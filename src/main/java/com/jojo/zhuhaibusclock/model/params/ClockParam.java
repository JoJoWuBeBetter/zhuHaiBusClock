package com.jojo.zhuhaibusclock.model.params;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;


/**
 * @author JoJoWu
 */
@Data
@ToString
public class ClockParam {

    private Long id;

    @NotNull(message = "userId不能为空")
    private Long userId;

    @NotNull(message = "闹钟提醒时间不能为空")
    private LocalTime alarmTime;

    private String repeatTime;

    private Boolean isEnable;

    @NotNull(message = "公交路线不能为空")
    private Long routeId;

    @NotNull(message = "公交路线不能为空")
    private Long segmentId;

    @NotNull(message = "车站不能为空")
    private String stationId;


}
