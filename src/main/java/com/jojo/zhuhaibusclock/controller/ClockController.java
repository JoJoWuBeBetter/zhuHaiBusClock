package com.jojo.zhuhaibusclock.controller;

import com.jojo.zhuhaibusclock.model.BaseResponse;
import com.jojo.zhuhaibusclock.model.params.ClockParam;
import com.jojo.zhuhaibusclock.model.vo.ClockVO;
import com.jojo.zhuhaibusclock.service.ClockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @author JoJoWu
 */
@Slf4j
@RestController
@RequestMapping(value = "/clock/")
public class ClockController {
    private final ClockService clockService;
    @Autowired
    private HttpServletRequest request;

    public ClockController(ClockService clockService) {
        this.clockService = clockService;
    }

    @PostMapping(value = "/addClock")
    public BaseResponse<Object> addClock(@RequestBody @Valid ClockParam clockParam) {
        return new BaseResponse<>(200, "闹钟添加成功", clockService.addClock(clockParam));
    }

    @PostMapping(value = "/updateClock")
    public BaseResponse<Object> updateClock(@RequestBody @Valid ClockParam clockParam) {
        return new BaseResponse<>(200, "闹钟修改成功", clockService.updateClock(clockParam));
    }

    @GetMapping(value = "/getClock")
    public ClockVO getClock(@RequestParam(name = "clockId") Long clockId) {
        return clockService.getClockVO(clockId);
    }

    @GetMapping(value = "/getClockList")
    public List<ClockVO> getClockList(@RequestParam(name = "userId") Long userId) {
        return clockService.getClockList(userId);
    }

    @GetMapping(value = "/pauseClock")
    public BaseResponse<Object> pauseClock(@RequestParam(name = "clockId") Long clockId) {
        clockService.pauseClock(clockId);
        return new BaseResponse<>(200, "闹钟暂停成功", null);
    }

    @GetMapping(value = "/resumeClock")
    public BaseResponse<Object> resumeClock(@RequestParam(name = "clockId") Long clockId) {
        clockService.resumeClock(clockId);
        return new BaseResponse<>(200, "闹钟开启成功", null);
    }

    @GetMapping(value = "/deleteClock")
    public BaseResponse<Object> deleteClock(@RequestParam(name = "clockId") Long clockId) {
        clockService.deleteClock(clockId);
        return new BaseResponse<>(200, "闹钟删除成功", null);
    }
}
