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
    /**
     * stationID
     */
    @JSONField(name = "StationID")
    private String stationId;
    /**
     * arriveTime
     */
    @JSONField(name = "ArriveTime")
    private String arriveTime;
    /**
     * arriveStaInfo
     */
    @JSONField(name = "ArriveStaInfo")
    private String arriveStaInfo;
    /**
     * nextStaInfo
     */
    @JSONField(name = "NextStaInfo")
    private String nextStaInfo;
    /**
     * busPostion
     */
    @JSONField(name = "BusPostion")
    private BusPostion busPostion;
    /**
     * productid
     */
    @JSONField(name = "Productid")
    private Long productId;
    /**
     * subRouteID
     */
    @JSONField(name = "SubRouteID")
    private Long subRouteId;
    /**
     * leaveOrStop
     */
    @JSONField(name = "LeaveOrStop")
    private Long leaveOrStop;

    /**
     * BusPostion
     */
    @NoArgsConstructor
    @Data
    public static class BusPostion {
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
