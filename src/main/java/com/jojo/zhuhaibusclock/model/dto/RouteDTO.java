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
    private String reverseRouteId;
    private String reverseSegmentId;
    private List<StationDTO> stations;
}
