package com.jojo.zhuhaibusclock.model.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author JoJoWu
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RouteDetailDTO extends RouteDTO {
    List<BusDTO> nearestBus;
}
