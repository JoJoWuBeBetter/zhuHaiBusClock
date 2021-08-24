package com.jojo.zhuhaibusclock.service.impl;


import com.alibaba.fastjson.JSON;
import com.jojo.zhuhaibusclock.exception.ClockException;
import com.jojo.zhuhaibusclock.exception.NotFoundException;
import com.jojo.zhuhaibusclock.mapper.SysClockMapper;
import com.jojo.zhuhaibusclock.mapper.SysUserClockMapper;
import com.jojo.zhuhaibusclock.model.SysClock;
import com.jojo.zhuhaibusclock.model.SysUserClock;
import com.jojo.zhuhaibusclock.model.dto.RouteDTO;
import com.jojo.zhuhaibusclock.model.entity.BusPos;
import com.jojo.zhuhaibusclock.model.params.ClockParam;
import com.jojo.zhuhaibusclock.model.result.RealtimeInfoListResult;
import com.jojo.zhuhaibusclock.model.vo.ClockVO;
import com.jojo.zhuhaibusclock.quartz.service.QuartzService;
import com.jojo.zhuhaibusclock.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JoJoWu
 */
@Slf4j
@Service
public class ClockServiceImpl implements ClockService {

    private final SysClockMapper clockMapper;
    private final SysUserClockMapper userClockMapper;
    private final MessageService messageService;
    private final RouteService routeService;
    private final ZhuHaiBusService zhuHaiBusService;
    private final QuartzService quartzService;


    public ClockServiceImpl(SysClockMapper clockMapper, SysUserClockMapper userClockMapper,
                            MessageService messageService, RouteService routeService, ZhuHaiBusService zhuHaiBusService, QuartzService quartzService) {

        this.clockMapper = clockMapper;
        this.userClockMapper = userClockMapper;
        this.messageService = messageService;
        this.routeService = routeService;
        this.zhuHaiBusService = zhuHaiBusService;
        this.quartzService = quartzService;
    }

    /**
     * 添加闹钟
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SysClock addClock(ClockParam clockParam) {
        SysClock clock = new SysClock();
        BeanUtils.copyProperties(clockParam, clock);

        clockMapper.insert(clock);

        SysUserClock userClock = new SysUserClock();
        userClock.setClockId(clock.getId());
        userClock.setUserId(clockParam.getUserId());
        userClockMapper.insert(userClock);

        quartzService.addClockSchedule(clock);
        return clock;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public SysClock updateClock(ClockParam clockParam) {
        SysClock clock = new SysClock();
        BeanUtils.copyProperties(clockParam, clock);
        if (clockMapper.selectById(clock.getId()) == null) {
            throw new NotFoundException("闹钟不存在");
        }
        clockMapper.updateById(clock);

        quartzService.deleteClockSchedule(clock.getId());
        quartzService.addClockSchedule(clock);
        return clock;
    }

    /**
     * 暂停闹钟
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void pauseClock(Long clockId) {
        SysClock clock = clockMapper.selectById(clockId);
        if (clock == null) {
            throw new NotFoundException("无法获取对应的闹钟");
        }
        if (!clock.getIsEnable()) {
            throw new ClockException("闹钟已经开启");
        }
        clock.setIsEnable(false);
        clockMapper.updateById(clock);

        quartzService.deleteClockSchedule(clock.getId());
    }

    /**
     * 重启闹钟
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void resumeClock(Long clockId) {
        SysClock clock = clockMapper.selectById(clockId);
        if (clock == null) {
            throw new NotFoundException("无法获取对应的闹钟");
        }
        if (clock.getIsEnable()) {
            throw new ClockException("闹钟已经关闭");
        }
        clock.setIsEnable(true);
        clockMapper.updateById(clock);

        quartzService.addClockSchedule(clock);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void deleteClock(Long clockId) {
        clockMapper.deleteById(clockId);
        userClockMapper.deleteByClockId(clockId);
        quartzService.deleteClockSchedule(clockId);
    }


    @Override
    public SysClock getClock(Long clockId) {
        SysClock clock = clockMapper.selectById(clockId);
        if (clock == null) {
            throw new NotFoundException("没有找到对应的闹钟");
        }
        return clock;
    }

    @Override
    public ClockVO getClockVO(Long clockId) {
        return sysClockToClockVO(getClock(clockId));
    }


    @Override
    public SysClock getClockAndUser(Long clockId) {
        return clockMapper.selectClockAndUserById(clockId);
    }

    @Override
    public List<ClockVO> getClockList(Long userId) {
        List<SysClock> clockList = clockMapper.selectClockByUserId(userId);
        List<ClockVO> clockVOList = new ArrayList<>();
        for (SysClock clock : clockList) {
            clockVOList.add(sysClockToClockVO(clock));
        }
        return clockVOList;
    }

    @Override
    public void goOffClock(Long clockId) {
        SysClock clock = getClockAndUser(clockId);
        RealtimeInfoListResult result = zhuHaiBusService.getRealtimeInfoList(clock.getStationId(), clock.getRouteId());
        log.info(JSON.toJSONString(result));
        List<BusPos> busPosList = result.getRealtimeInfoList();
        BusPos nearestBus = null;
        for (BusPos bus : busPosList) {
            if (nearestBus == null) {
                nearestBus = bus;
            } else if (nearestBus.getSpaceNum() > bus.getSpaceNum() && bus.getSpaceNum() > 0) {
                nearestBus = bus;
            }
        }
        if (nearestBus != null) {
            String title = "珠海公交闹钟提醒您";
            String msg;
            if (nearestBus.getDistance() != null) {
                msg = String.format("%s已经抵达%s,还有%d站到达%s", result.getRouteName(), nearestBus.getArriveStaName(), nearestBus.getSpaceNum(), result.getStationName());
            } else {
                msg = String.format("%s%s开往%s", result.getRouteName(), nearestBus.getForeCastInfo1(), result.getStationName());
            }
            messageService.pushMessage(clock.getUser().getBarkKey(), title, msg);
        }
    }


    private ClockVO sysClockToClockVO(SysClock sysClock) {
        ClockVO clockVO = new ClockVO();
        BeanUtils.copyProperties(sysClock, clockVO);
        clockVO.setRepeatTime(sysClock.getRepeatTime() != null ? sysClock.getRepeatTime().split(",") : null);

        RouteDTO routeDTO = routeService.getRouteDetail(sysClock.getRouteId(), sysClock.getSegmentId());

        routeDTO.getStations().forEach(stationDTO -> {
            if (stationDTO.getStationId().equals(sysClock.getStationId())) {
                clockVO.setStationName(stationDTO.getStationName());
            }
        });

        clockVO.setRouteName(routeDTO.getRouteName());
        clockVO.setSegmentName(routeDTO.getSegmentName());
        return clockVO;
    }
}
