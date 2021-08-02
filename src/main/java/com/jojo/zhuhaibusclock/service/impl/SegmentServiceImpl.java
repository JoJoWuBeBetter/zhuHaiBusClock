package com.jojo.zhuhaibusclock.service.impl;

import com.jojo.zhuhaibusclock.exception.NotFoundException;
import com.jojo.zhuhaibusclock.mapper.SysSegmentMapper;
import com.jojo.zhuhaibusclock.model.SysSegment;
import com.jojo.zhuhaibusclock.model.SysSegmentKey;
import com.jojo.zhuhaibusclock.model.entity.Segment;
import com.jojo.zhuhaibusclock.model.entity.Station;
import com.jojo.zhuhaibusclock.model.result.RouteRunningDetailResult;
import com.jojo.zhuhaibusclock.model.result.StationSegmentListResult;
import com.jojo.zhuhaibusclock.service.SegmentService;
import com.jojo.zhuhaibusclock.service.ZhuHaiBusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author JoJoWu
 */
@Service
@Slf4j
public class SegmentServiceImpl implements SegmentService {

    private final SysSegmentMapper segmentMapper;
    private final ZhuHaiBusService zhuHaiBusService;

    @Autowired
    public SegmentServiceImpl(SysSegmentMapper segmentMapper, ZhuHaiBusService zhuHaiBusService) {
        this.segmentMapper = segmentMapper;
        this.zhuHaiBusService = zhuHaiBusService;
    }

    @Override
    public int addSegment(SysSegment segments) {
        return 0;
    }

    @Override
    @Cacheable(cacheNames = "segment", key = "#segmentId + '&' + #routeId")
    public SysSegment findSegment(Long segmentId, Long routeId) {
        AtomicReference<SysSegment> segment = new AtomicReference<>(segmentMapper.selectBySegmentIdAndRouteId(segmentId, routeId));

        if (segment.get() == null) {
            RouteRunningDetailResult result = zhuHaiBusService.getRouteRunningDetail(routeId.toString(), segmentId.toString());
            Station station = result.getStations().get(0);
            if (station == null) {
                throw new NotFoundException("获取segment错误");
            }
            StationSegmentListResult segmentListResult = zhuHaiBusService.getStationSegmentList(station.getStationId());
            segmentListResult.getSegmentList().forEach(
                    segmentRaw -> {
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
    public void deleteSegment(Long segmentId, Long routeId) {
        SysSegmentKey segmentKey = new SysSegment();
        segmentKey.setSegmentId(segmentId);
        segmentKey.setRouteId(routeId);
        segmentMapper.deleteByPrimaryKey(segmentKey);
    }

    private SysSegment segmentRawToSysSegment(Segment segmentRaw) {
        SysSegment sysSegment = new SysSegment();
        BeanUtils.copyProperties(segmentRaw, sysSegment);
        sysSegment.setFirstTime(segmentRaw.getFirstTime().toLocalTime());
        sysSegment.setLastTime(segmentRaw.getLastTime().toLocalTime());
        return sysSegment;
    }
}
