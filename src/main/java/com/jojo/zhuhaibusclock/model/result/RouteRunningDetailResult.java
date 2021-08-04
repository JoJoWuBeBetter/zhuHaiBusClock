package com.jojo.zhuhaibusclock.model.result;


import com.alibaba.fastjson.annotation.JSONField;
import com.jojo.zhuhaibusclock.model.entity.BusPos;
import com.jojo.zhuhaibusclock.model.entity.Route;
import com.jojo.zhuhaibusclock.model.entity.Station;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author JoJoWu
 */

@NoArgsConstructor
@Data
public class RouteRunningDetailResult {

    @JSONField(name = "RouteID")
    private String routeId;
    @JSONField(name = "SegmentID")
    private String segmentId;
    @JSONField(name = "BusPosList")
    private List<BusPos> busPosList;
    @JSONField(name = "GpsTime")
    private Integer gpsTime;
    @JSONField(name = "stations")
    private List<Station> stations;
    @JSONField(name = "nearestbus")
    private List<BusPos> nearestBus;
    @JSONField(name = "routes")
    private List<Route> routes;
}
