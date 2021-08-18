package com.jojo.zhuhaibusclock.model.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.jojo.zhuhaibusclock.model.entity.BusPos;
import com.jojo.zhuhaibusclock.model.entity.BusPos.BusPosition;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JoJoWu
 */
@NoArgsConstructor
@Data
public class BusDTO {
    /**
     * arriveStaInfo
     */
    @JSONField(name = "ArriveStaInfo")
    private String arriveStaInfo;
    /**
     * arriveTime
     */
    @JSONField(name = "ArriveTime")
    private String arriveTime;
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
     * busPostion
     */
    @JSONField(name = "BusPosition")
    private BusPosition busPosition;
    /**
     * leaveOrStop
     */
    @JSONField(name = "LeaveOrStop")
    private Integer leaveOrStop;
    /**
     * nextStaInfo
     */
    @JSONField(name = "NextStaInfo")
    private String nextStaInfo;
    /**
     * productid
     */
    @JSONField(name = "Productid")
    private String productId;
    /**
     * spaceNum
     */
    @JSONField(name = "SpaceNum")
    private Integer spaceNum;
    /**
     * stationID
     */
    @JSONField(name = "StationID")
    private String stationId;
    /**
     * subRouteID
     */
    @JSONField(name = "SubRouteID")
    private Integer subRouteId;
}
