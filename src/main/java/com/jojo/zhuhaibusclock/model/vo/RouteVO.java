package com.jojo.zhuhaibusclock.model.vo;

import com.jojo.zhuhaibusclock.model.dto.RouteDTO;
import com.jojo.zhuhaibusclock.model.dto.StationDTO;
import lombok.Data;

import java.util.List;

/**
 * @author JoJoWu
 */
@Data
public class RouteVO {
    private String routeId;
    private String segmentId;
    private String routeName;
    private String segmentName;
    private String reverseRouteId;
    private String reverseSegmentId;
    private List<StationVO> stations;
}
