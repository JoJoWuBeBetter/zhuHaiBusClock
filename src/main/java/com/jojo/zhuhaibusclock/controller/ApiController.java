package com.jojo.zhuhaibusclock.controller;

import com.jojo.zhuhaibusclock.model.entity.Token;
import com.jojo.zhuhaibusclock.model.vo.RouteVO;
import com.jojo.zhuhaibusclock.service.RouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(value = "/checkWxValid")
    public String checkWxValid(@RequestParam(name = "signature") String signature,
                                 @RequestParam(name = "timestamp") String timestamp,
                                 @RequestParam(name = "nonce") String nonce,
                                 @RequestParam(name = "echostr") String echostr) {
        log.info("11111111111111111");
        return echostr;
    }
}
