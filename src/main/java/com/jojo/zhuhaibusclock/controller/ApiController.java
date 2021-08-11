package com.jojo.zhuhaibusclock.controller;

import com.jojo.zhuhaibusclock.model.BaseResponse;
import com.jojo.zhuhaibusclock.model.entity.Route;
import com.jojo.zhuhaibusclock.model.params.ClockParam;
import com.jojo.zhuhaibusclock.model.vo.ClockVO;
import com.jojo.zhuhaibusclock.model.vo.RouteVO;
import com.jojo.zhuhaibusclock.service.RouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author JoJoWu
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/")
public class ApiController {
    private final RouteService routeService;

    public ApiController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping(value = "/getRoute")
    public RouteVO getRoute(@RequestParam(name = "routeId") String routeId, @RequestParam(name = "segmentId") String segmentId) {
        return routeService.getRouteVO(routeId, segmentId);
    }
}
