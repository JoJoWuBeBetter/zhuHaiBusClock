package com.jojo.zhuhaibusclock.model.vo;

import com.jojo.zhuhaibusclock.model.dto.StationDTO;
import com.jojo.zhuhaibusclock.model.entity.Station;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

/**
 * @author JoJoWu
 */
@Data
public class ClockVO {
    /**
     * 闹钟ID
     */
    private Long id;

    /**
     * 闹钟提醒时间
     */
    private LocalTime alarmTime;

    /**
     * 重复时间
     */
    private String[] repeatTime;

    /**
     * 是否启用
     */
    private Boolean isEnable;

    /**
     * 路线ID
     */
    private String routeId;

    /**
     * 路线名称
     */
    private String routeName;

    /**
     * 辅助路线ID查询
     */
    private String segmentId;

    private String segmentName;

    /**
     * 车站ID
     */
    private String stationId;

    /**
     * 车站名
     */
    private String stationName;
}
