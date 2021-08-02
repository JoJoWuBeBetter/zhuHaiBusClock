package com.jojo.zhuhaibusclock.model.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author JoJoWu
 */
@NoArgsConstructor
@Data
public class Segment {
    @JSONField(name = "SegmentID")
    private String segmentId;
    @JSONField(name = "FirstTime", format = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime firstTime;
    @JSONField(name = "LastTime", format = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime lastTime;
    @JSONField(name = "RoutePrice")
    private Integer routePrice;
    @JSONField(name = "RunDirection")
    private Integer runDirection;
    @JSONField(name = "RouteID")
    private String routeId;
    @JSONField(name = "RouteName")
    private String routeName;
    @JSONField(name = "StationID")
    private String stationId;
    @JSONField(name = "RouteType")
    private Integer routeType;
    @JSONField(name = "startat")
    private String startAt;
    @JSONField(name = "endat")
    private String endAt;
}
