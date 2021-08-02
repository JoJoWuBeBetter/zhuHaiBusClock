package com.jojo.zhuhaibusclock.model;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.Data;

/**
 * sys_segment
 * @author 
 */
@Data
public class SysSegment extends SysSegmentKey implements Serializable {
    /**
     * 首班车发车时间
     */
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonSerialize(using = LocalTimeSerializer.class)
    private LocalTime firstTime;

    /**
     * 末班车发车时间
     */
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonSerialize(using = LocalTimeSerializer.class)
    private LocalTime lastTime;

    /**
     * 票价
     */
    private Integer routePrice;

    /**
     * 行驶方向
     */
    private Integer runDirection;

    /**
     * 公交路线名
     */
    private String routeName;

    /**
     * 车站ID
     */
    private String stationId;

    /**
     * 路线类型
     */
    private Integer routeType;

    /**
     * 首发站
     */
    private String startAt;

    /**
     * 终点站
     */
    private String endAt;

    private static final long serialVersionUID = 1L;
}