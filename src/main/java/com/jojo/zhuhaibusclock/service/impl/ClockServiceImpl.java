package com.jojo.zhuhaibusclock.service.impl;


import com.jojo.zhuhaibusclock.exception.ClockException;
import com.jojo.zhuhaibusclock.exception.NotFoundException;
import com.jojo.zhuhaibusclock.mapper.SysClockMapper;
import com.jojo.zhuhaibusclock.mapper.SysUserClockMapper;
import com.jojo.zhuhaibusclock.model.SysClock;
import com.jojo.zhuhaibusclock.model.SysUserClock;
import com.jojo.zhuhaibusclock.model.dto.RouteDTO;
import com.jojo.zhuhaibusclock.model.params.ClockParam;
import com.jojo.zhuhaibusclock.model.vo.ClockVO;
import com.jojo.zhuhaibusclock.model.vo.StationVO;
import com.jojo.zhuhaibusclock.service.ClockService;
import com.jojo.zhuhaibusclock.service.MessageService;
import com.jojo.zhuhaibusclock.service.RouteService;
import com.jojo.zhuhaibusclock.service.StationService;
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
    private final StationService stationService;
    private final RouteService routeService;


    public ClockServiceImpl(SysClockMapper clockMapper, SysUserClockMapper userClockMapper,
                            MessageService messageService, StationService stationService, RouteService routeService) {

        this.clockMapper = clockMapper;
        this.userClockMapper = userClockMapper;
        this.messageService = messageService;
        this.stationService = stationService;
        this.routeService = routeService;
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
        return clock;
    }

    @Override
    public SysClock updateClock(ClockParam clockParam) {
        SysClock clock = new SysClock();
        BeanUtils.copyProperties(clockParam, clock);
        if (clockMapper.selectById(clock.getId()) == null) {
            throw new NotFoundException("闹钟不存在");
        }
        clockMapper.updateById(clock);
        return clock;
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

    //TODO 闹钟提醒未完成
    @Override
    public void goOffClock(Long clockId) {
        SysClock clock = getClockAndUser(clockId);
    }

    /**
     * 暂停闹钟
     */
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
    }

    /**
     * 暂停闹钟
     */
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
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void deleteClock(Long clockId) {
        clockMapper.deleteById(clockId);
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
