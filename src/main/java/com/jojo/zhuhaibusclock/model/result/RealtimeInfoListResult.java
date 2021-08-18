package com.jojo.zhuhaibusclock.model.result;

import com.alibaba.fastjson.annotation.JSONField;
import com.jojo.zhuhaibusclock.model.entity.BusPos;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author JoJoWu
 */
@NoArgsConstructor
@Data
public class RealtimeInfoListResult {
    /**
     * routeID
     */
    @JSONField(name = "RouteID")
    private Integer routeId;
    /**
     * realtimeInfoList
     */
    @JSONField(name = "RealtimeInfoList")
    private List<BusPos> realtimeInfoList;
    /**
     * stationID
     */
    @JSONField(name = "StationID")
    private String stationId;
    /**
     * stationName
     */
    @JSONField(name = "StationName")
    private String stationName;
    /**
     * routeName
     */
    @JSONField(name = "RouteName")
    private String routeName;
    /**
     * endStaInfo
     */
    @JSONField(name = "EndStaInfo")
    private String endStaInfo;
    /**
     * firstTime
     */
    @JSONField(name = "FirstTime")
    private String firstTime;
    /**
     * lastTime
     */
    @JSONField(name = "LastTime")
    private String lastTime;
    /**
     * firtLastShiftInfo
     */
    @JSONField(name = "FirtLastShiftInfo")
    private String firtLastShiftInfo;
    /**
     * departureState
     */
    @JSONField(name = "DepartureState")
    private String departureState;
    /**
     * serverTime
     */
    @JSONField(name = "ServerTime")
    private String serverTime;
    /**
     * sortinfo
     */
    @JSONField(name = "Sortinfo")
    private Integer sortinfo;
    /**
     * sortinfo2
     */
    @JSONField(name = "Sortinfo2")
    private Integer sortinfo2;

}
