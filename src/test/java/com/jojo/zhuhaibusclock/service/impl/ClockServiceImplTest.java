package com.jojo.zhuhaibusclock.service.impl;

import com.alibaba.fastjson.JSON;
import com.jojo.zhuhaibusclock.model.vo.ClockVO;
import com.jojo.zhuhaibusclock.service.ClockService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ClockServiceImplTest {
    @Autowired
    ClockService clockService;

    @Test
    public void addClock() {
    }

    @Test
    public void updateClock() {
    }

    @Test
    public void getClock() {
    }

    @Test
    public void getClockVO() {
        ClockVO clockVO = clockService.getClockVO(13L);
        Assert.assertNotNull(clockVO);
        log.info(JSON.toJSONString(clockVO));
    }

    @Test
    public void getClockAndUser() {
    }

    @Test
    public void getClockList() {
    }

    @Test
    public void goOffClock() {
    }

    @Test
    public void pauseClock() {
    }

    @Test
    public void resumeClock() {
    }

    @Test
    public void deleteClock() {
    }
}