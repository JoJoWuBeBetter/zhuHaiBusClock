package com.jojo.zhuhaibusclock.service;

import com.jojo.zhuhaibusclock.model.SysSegment;
import com.jojo.zhuhaibusclock.model.dto.RouteDTO;
import com.jojo.zhuhaibusclock.model.result.RouteRunningDetailResult;

/**
 * @author JoJoWu
 */
public interface RouteService {
    int addSegment(SysSegment segments);

    SysSegment findSegment(String segmentId, String routeId);

    void updateSegment(SysSegment segment);

    void deleteSegment(String segmentId, String routeId);

    RouteDTO getRoute(String routeId, String segmentId, String stationId);
}
