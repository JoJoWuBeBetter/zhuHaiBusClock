package com.jojo.zhuhaibusclock.service.impl;

import com.jojo.zhuhaibusclock.exception.NotFoundException;
import com.jojo.zhuhaibusclock.mapper.SysStationMapper;
import com.jojo.zhuhaibusclock.model.SysStation;
import com.jojo.zhuhaibusclock.model.entity.Station;
import com.jojo.zhuhaibusclock.model.result.StationSegmentListResult;
import com.jojo.zhuhaibusclock.service.StationService;
import com.jojo.zhuhaibusclock.service.ZhuHaiBusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author JoJoWu
 */
@Slf4j
@Service
public class StationServiceImpl implements StationService {
    @Resource
    private  SysStationMapper stationMapper;

    private final ZhuHaiBusService zhuHaiBusService;

    public StationServiceImpl(ZhuHaiBusService zhuHaiBusService) {
        this.zhuHaiBusService = zhuHaiBusService;
    }


    @Override
    public int addStation(SysStation station) {
        return stationMapper.insert(station);
    }

    @Override
    @Cacheable(cacheNames = "station", key = "#stationId")
    public SysStation findStation(String stationId) {
        SysStation station = stationMapper.selectByPrimaryKey(stationId);

        if (station == null) {
            station = new SysStation();

            StationSegmentListResult result = zhuHaiBusService.getStationSegmentList(stationId);
            Station stationInfo = result.getStationInfo();

            if (stationInfo == null) {
                throw new NotFoundException("没有找到对应的车站");
            }

            BeanUtils.copyProperties(stationInfo, station);
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//            station.setCreatedAt(LocalDateTime.parse(stationInfo.getCreatedAt(), formatter));
//            station.setUpdatedAt(LocalDateTime.parse(stationInfo.getUpdatedAt(), formatter));
            log.info(station.toString());
            stationMapper.insert(station);
        }
        return station;
    }

    @Override
    @CacheEvict(cacheNames = "station",key = "#stationId")
    public void deleteStation(String stationId) {
        stationMapper.deleteByPrimaryKey(stationId);
    }

    @Override
    public void updateStation(SysStation sysStation) {
        stationMapper.updateByPrimaryKeySelective(sysStation);
    }


}
