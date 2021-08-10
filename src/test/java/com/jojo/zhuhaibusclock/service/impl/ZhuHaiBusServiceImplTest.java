package com.jojo.zhuhaibusclock.service.impl;

import com.alibaba.fastjson.JSON;
import com.jojo.zhuhaibusclock.model.result.RouteRunningDetailResult;
import com.jojo.zhuhaibusclock.service.ZhuHaiBusService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ZhuHaiBusServiceImplTest {
    @Autowired
    ZhuHaiBusService zhuHaiBusService;

    @Test
    public void searchBusByKeyword() {
    }

    @Test
    public void getRouteRunningDetail() {
        RouteRunningDetailResult result = zhuHaiBusService.getRouteRunningDetail("235", "60264", "66201706090919319171");
        Assert.assertNotNull(result);
        log.info(JSON.toJSONString(result));
         result = zhuHaiBusService.getRouteRunningDetail("235", "60264");
        Assert.assertNotNull(result);
        log.info(JSON.toJSONString(result));
    }

    @Test
    public void getStationSegmentList() {
    }
}