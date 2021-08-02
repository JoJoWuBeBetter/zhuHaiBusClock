package com.jojo.zhuhaibusclock.controller;

import com.jojo.zhuhaibusclock.model.result.StationSegmentListResult;
import com.jojo.zhuhaibusclock.model.result.RouteRunningDetailResult;
import com.jojo.zhuhaibusclock.model.result.SearchBusByKeywordResult;
import com.jojo.zhuhaibusclock.service.ZhuHaiBusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author JoJoWu
 */
@Slf4j
@Api(tags = "珠海公交巴士API")
@RestController
@RequestMapping(value = "/zhuHaiBus/")
public class ZhuHaiBusApiController {
    private final ZhuHaiBusService zhuHaiBusService;

    public ZhuHaiBusApiController(ZhuHaiBusService zhuHaiBusService) {
        this.zhuHaiBusService = zhuHaiBusService;
    }

    @ApiOperation("通过关键字查询公交路线")
    @GetMapping(value = "/searchBusByKeyword")
    public SearchBusByKeywordResult searchBusByKeyword(@RequestParam(name = "type", required = true) int type,
                                                       @RequestParam(name = "keyword", required = true) String keyword) throws IOException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
        if ("".equals(keyword)) {
            return null;
        }
        return zhuHaiBusService.searchBusByKeyword(type, keyword);
    }

    @ApiOperation("获得正在行驶的路线详细信息")
    @GetMapping(value = "/getRouteRunningDetail")
    public RouteRunningDetailResult getRouteRunningDetail(@RequestParam(name = "routeId", required = true) String routeId,
                                                          @RequestParam(name = "segmentId", required = true) String segmentId) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, IOException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        return zhuHaiBusService.getRouteRunningDetail(routeId, segmentId);
    }

    @ApiOperation("查找指定车站的公交")
    @GetMapping(value = "/getStationSegmentList")
    public StationSegmentListResult getStationSegmentListResult(@RequestParam(name = "stationId") String stationId) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, IOException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        return zhuHaiBusService.getStationSegmentList(stationId);
    }

}
