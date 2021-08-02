package com.jojo.zhuhaibusclock.model;

import java.io.Serializable;
import java.time.LocalTime;

import lombok.Data;

/**
 * sys_clock
 *
 * @author JoJoWu
 */
@Data
public class SysClock implements Serializable {
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
    private String repeatTime;

    /**
     * 是否启用
     */
    private Boolean isEnable;

    /**
     * 路线ID
     */
    private String routeId;

    /**
     * 辅助路线ID查询
     */
    private String segmentId;

    /**
     * 车站ID
     */
    private String stationId;

    private SysUser user;

    private static final long serialVersionUID = 1L;
}