package com.jojo.zhuhaibusclock.controller;

import com.jojo.zhuhaibusclock.model.BaseResponse;
import com.jojo.zhuhaibusclock.model.entity.Token;
import com.jojo.zhuhaibusclock.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
