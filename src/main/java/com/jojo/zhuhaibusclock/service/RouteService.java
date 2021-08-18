package com.jojo.zhuhaibusclock.service;

import com.jojo.zhuhaibusclock.model.SysSegment;
import com.jojo.zhuhaibusclock.model.dto.RouteDTO;
import com.jojo.zhuhaibusclock.model.dto.RouteDetailDTO;
import com.jojo.zhuhaibusclock.model.result.RouteRunningDetailResult;
import com.jojo.zhuhaibusclock.model.vo.RouteVO;

/**
 * @author JoJoWu
 */
public interface RouteService {
    int addSegment(SysSegment segments);

    SysSegment findSegment(String segmentId, String routeId);

    void updateSegment(SysSegment segment);

    void deleteSegment(String segmentId, String routeId);

    RouteDetailDTO getRouteRunningDetail(String routeId, String segmentId, String stationId);

    RouteDTO getRouteDetail(String routeId, String segmentId);

    RouteVO getRouteVO(String routeId, String segmentId);
}
