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
    private String routeID;
    @JSONField(name = "SegmentID")
    private String segmentID;
    @JSONField(name = "BusPosList")
    private List<BusPos> busPosList;
    @JSONField(name = "GpsTime")
    private Integer gpsTime;
    @JSONField(name = "stations")
    private List<Station> stations;
    @JSONField(name = "routes")
    private List<Route> routes;
}
