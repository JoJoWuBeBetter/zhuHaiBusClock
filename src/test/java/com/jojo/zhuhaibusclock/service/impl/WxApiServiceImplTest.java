package com.jojo.zhuhaibusclock.service.impl;

import com.jojo.zhuhaibusclock.service.WxApiService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class WxApiServiceImplTest {
    @Autowired
    WxApiService wxApiService;

    @Test
    public void jsCodeToSession() {
    }

    @Test
    public void getAccessToken() {
        log.info(wxApiService.getAccessToken().toString());
    }
}