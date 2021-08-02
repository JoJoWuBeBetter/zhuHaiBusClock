package com.jojo.zhuhaibusclock.model.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author JoJoWu
 */
@NoArgsConstructor
@Data
public class Station {
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
     * 车站序号
     */
    @JSONField(name = "StationNO")
    private Integer stationNO;
    /**
     * 经度
     */
    @JSONField(name = "Longitude")
    private String longitude;
    /**
     * 纬度
     */
    @JSONField(name = "Latitude")
    private String latitude;
    /**
     * 车站备注
     */
    @JSONField(name = "Stationmemo")
    private String stationmemo;
    /**
     * dualSerial
     */
    @JSONField(name = "DualSerial")
    private Integer dualSerial;
    /**
     * stationSort
     */
    @JSONField(name = "StationSort")
    private Integer stationSort;
    /**
     * createdAt
     */
    @JSONField(name = "created_at", format = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdAt;
    /**
     * updatedAt
     */
    @JSONField(name = "updated_at", format = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime updatedAt;
}
