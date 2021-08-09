package com.jojo.zhuhaibusclock.model.vo;

import lombok.Data;

import java.time.LocalTime;

/**
 * @author JoJoWu
 */
@Data
public class RouteVO {
    private LocalTime firstTime;
    private LocalTime lastTime;
    private String routeId;
    private String routeName;
    private String routePrice;
    private Integer routeType;
    private Integer runDirection;
    private String segmentId;
    private String segmentName;
}
