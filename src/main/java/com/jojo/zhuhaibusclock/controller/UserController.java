package com.jojo.zhuhaibusclock.controller;

import com.jojo.zhuhaibusclock.model.BaseResponse;
import com.jojo.zhuhaibusclock.model.entity.Token;
import com.jojo.zhuhaibusclock.model.params.BarkUrlParam;
import com.jojo.zhuhaibusclock.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

/**
 * @author JoJoWu
 */
@Slf4j
@RestController
@RequestMapping(value = "/user/")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/login")
    public Token login(@RequestParam(name = "code") String code) throws IOException {
        return userService.getToken(code);
    }

    @GetMapping(value = "/checkBarkKey")
    public boolean checkBarkKey(@RequestParam(name = "userId") Long userId) {
        return userService.checkBarkKey(userId);
    }

    @PostMapping(value = "/updateBarkUrl")
    public BaseResponse<Object> updateBarkUrl(@RequestBody @Valid BarkUrlParam barkUrlParam) {
        log.info(barkUrlParam.toString());
        return new BaseResponse<>(200, "BarkKey上传成功", userService.updateBarkKey(barkUrlParam.getUserId(), barkUrlParam.getBarkUrl()));
    }
}
