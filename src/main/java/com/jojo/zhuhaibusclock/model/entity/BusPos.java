package com.jojo.zhuhaibusclock.model.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JoJoWu
 */
@NoArgsConstructor
@Data
public class BusPos {
    /**
     * busID
     */
    @JSONField(name = "BusID")
    private String busId;
    /**
     * busName
     */
    @JSONField(name = "BusName")
    private String busName;

    @JSONField(name = "ArriveStaName")
    private String arriveStaName;

    @JSONField(name = "ArriveStaInfo")
    private String arriveStaInfo;

    @JSONField(name = "ArriveTime")
    private String arriveTime;

    @JSONField(name = "BusPostion")
    private BusPosition busPosition;
    /**
     * stationID
     */
    @JSONField(name = "StationID")
    private String stationId;

    @JSONField(name = "SpaceNum")
    private Integer spaceNum;

    @JSONField(name = "RunTime")
    private Integer runTime;

    @JSONField(name = "Distance")
    private Integer distance;

    @JSONField(name = "ISLast")
    private Integer iSLast;

    @JSONField(name = "ForeCastInfo1")
    private String foreCastInfo1;

    @JSONField(name = "ForeCastInfo2")
    private String foreCastInfo2;

    @JSONField(name = "Areaconglevel")
    private String areaconglevel;

    @JSONField(name = "Temperature")
    private String temperature;

    @JSONField(name = "Productid")
    private String productId;

    @JSONField(name = "SubRouteID")
    private Integer subRouteId;

    @JSONField(name = "LeaveOrStop")
    private Integer leaveOrStop;

    @JSONField(name = "DepartureState")
    private String departureState;

    @JSONField(name = "SortTime")
    private Integer sortTime;

    @JSONField(name = "NextStaInfo")
    private String nextStaInfo;


    /**
     * BusPostion
     */
    @NoArgsConstructor
    @Data
    public static class BusPosition {
        /**
         * longitude
         */
        @JSONField(name = "Longitude")
        private Double longitude;
        /**
         * latitude
         */
        @JSONField(name = "Latitude")
        private Double latitude;
    }
}
