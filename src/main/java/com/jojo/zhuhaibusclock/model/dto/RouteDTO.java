package com.jojo.zhuhaibusclock.model.dto;

import lombok.Data;

import java.util.List;

/**
 * @author JoJoWu
 */
@Data
public class RouteDTO {
    private String routeId;
    private String segmentId;
    private String routeName;
    private String segmentName;
    private RouteDTO reverseRoute;
    private List<StationDTO> stations;
}
