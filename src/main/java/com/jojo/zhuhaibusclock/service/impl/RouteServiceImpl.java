package com.jojo.zhuhaibusclock.service.impl;

import com.jojo.zhuhaibusclock.exception.NotFoundException;
import com.jojo.zhuhaibusclock.mapper.SysSegmentMapper;
import com.jojo.zhuhaibusclock.model.SysSegment;
import com.jojo.zhuhaibusclock.model.SysSegmentKey;
import com.jojo.zhuhaibusclock.model.dto.RouteDTO;
import com.jojo.zhuhaibusclock.model.dto.StationDTO;
import com.jojo.zhuhaibusclock.model.entity.Segment;
import com.jojo.zhuhaibusclock.model.entity.Station;
import com.jojo.zhuhaibusclock.model.result.RouteRunningDetailResult;
import com.jojo.zhuhaibusclock.model.result.StationSegmentListResult;
import com.jojo.zhuhaibusclock.service.RouteService;
import com.jojo.zhuhaibusclock.service.ZhuHaiBusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author JoJoWu
 */
@Service
@Slf4j
public class RouteServiceImpl implements RouteService {

    private final SysSegmentMapper segmentMapper;
    private final ZhuHaiBusService zhuHaiBusService;

    @Autowired
    public RouteServiceImpl(SysSegmentMapper segmentMapper, ZhuHaiBusService zhuHaiBusService) {
        this.segmentMapper = segmentMapper;
        this.zhuHaiBusService = zhuHaiBusService;
    }

    @Override
    public int addSegment(SysSegment segments) {
        return 0;
    }

    @Override
    @Cacheable(cacheNames = "segment", key = "#segmentId + '&' + #routeId")
    public SysSegment findSegment(String segmentId, String routeId) {
        AtomicReference<SysSegment> segment = new AtomicReference<>(segmentMapper.selectBySegmentIdAndRouteId(segmentId, routeId));

        if (segment.get() == null) {
            RouteRunningDetailResult result = zhuHaiBusService.getRouteRunningDetail(routeId, segmentId);
            Station station = result.getStations().get(1);
            if (station == null) {
                throw new NotFoundException("获取segment错误");
            }
            StationSegmentListResult segmentListResult = zhuHaiBusService.getStationSegmentList(station.getStationId());
            segmentListResult.getSegmentList().forEach(
                    segmentRaw -> {
                        log.info("1111111111111111111111111111111");
                        if (segmentMapper.selectBySegmentIdAndRouteId(segmentRaw.getSegmentId(), segmentRaw.getRouteId()) == null) {
                            SysSegment sysSegment = segmentRawToSysSegment(segmentRaw);
                            segmentMapper.insert(sysSegment);
                        }
                        if (segmentId.equals(segmentRaw.getSegmentId()) && routeId.equals(segmentRaw.getRouteId())) {
                            segment.set(segmentRawToSysSegment(segmentRaw));
                        }
                    }
            );
        }

        if (segment.get() == null) {
            throw new NotFoundException("获取segment错误");
        }
        return segment.get();
    }

    @Override
    public void updateSegment(SysSegment segment) {

    }

    @Override
    @CacheEvict(cacheNames = "segment", key = "#segmentId + '&' + #routeId")
    public void deleteSegment(String segmentId, String routeId) {
        SysSegmentKey segmentKey = new SysSegment();
        segmentKey.setSegmentId(segmentId);
        segmentKey.setRouteId(routeId);
        segmentMapper.deleteByPrimaryKey(segmentKey);
    }

    @Override
    public RouteDTO getRoute(String routeId, String segmentId, String stationId) {
        RouteRunningDetailResult result = zhuHaiBusService.getRouteRunningDetail(routeId, segmentId, stationId);
        if (result == null) {
            throw new NotFoundException("没有找到对应路线");
        }
        RouteDTO routeDTO = new RouteDTO();
        routeDTO.setStations(new ArrayList<>());
        result.getRoutes().forEach(route -> {
            if (routeId.equals(route.getRouteId()) && segmentId.equals(route.getSegmentId())) {
                BeanUtils.copyProperties(route, routeDTO);
            }
        });
        result.getStations().forEach(station -> {
            StationDTO stationDTO = new StationDTO();
            BeanUtils.copyProperties(station, stationDTO);
            routeDTO.getStations().add(stationDTO);
        });
        return routeDTO;
    }

    private SysSegment segmentRawToSysSegment(Segment segmentRaw) {
        SysSegment sysSegment = new SysSegment();
        BeanUtils.copyProperties(segmentRaw, sysSegment);
        sysSegment.setFirstTime(segmentRaw.getFirstTime().toLocalTime());
        sysSegment.setLastTime(segmentRaw.getLastTime().toLocalTime());
        return sysSegment;
    }
}
