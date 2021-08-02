package com.jojo.zhuhaibusclock.model.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JoJoWu
 */
@NoArgsConstructor
@Data
public class Route {
    @JSONField(name = "RouteID")
    private String routeId;
    @JSONField(name = "Ismainsub")
    private Integer ismainsub;
    @JSONField(name = "isonroad")
    private Integer isonroad;
    @JSONField(name = "SegmentID")
    private String segmentId;
    @JSONField(name = "RouteName")
    private String routeName;
    @JSONField(name = "RouteType")
    private Integer routeType;
    @JSONField(name = "RouteNameExt")
    private Object routeNameExt;
    @JSONField(name = "RoutePrice")
    private String routePrice;
    @JSONField(name = "SegmentName")
    private String segmentName;
    @JSONField(name = "isshow")
    private Integer isshow;
    @JSONField(name = "customshowtime")
    private Object customshowtime;
    @JSONField(name = "RunDirection")
    private Integer runDirection;
    @JSONField(name = "FirstTime")
    private String firstTime;
    @JSONField(name = "LastTime")
    private String lastTime;
}
