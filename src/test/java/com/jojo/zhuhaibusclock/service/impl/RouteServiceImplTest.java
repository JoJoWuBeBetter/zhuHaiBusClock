package com.jojo.zhuhaibusclock.service.impl;

import com.alibaba.fastjson.JSON;
import com.jojo.zhuhaibusclock.model.SysSegment;
import com.jojo.zhuhaibusclock.model.dto.RouteDTO;
import com.jojo.zhuhaibusclock.model.dto.RouteDetailDTO;
import com.jojo.zhuhaibusclock.model.vo.RouteVO;
import com.jojo.zhuhaibusclock.service.RouteService;
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
public class RouteServiceImplTest {
    @Autowired
    RouteService routeService;

    @Test
    public void findSegmentTest() {
        SysSegment segment = routeService.findSegment("2685", "205");
        Assert.assertNotNull(segment);
        Assert.assertEquals(segment.getRouteName(), "25路");
    }

    @Test
    public void deleteSegment() {
        Assert.assertNotNull(routeService.findSegment("2685", "205"));
        routeService.deleteSegment("2685", "205");
    }

    @Test
    public void getRouteDetail() {
        RouteDTO routeDTO = routeService.getRouteDetail("235", "60264");
        Assert.assertNotNull(routeDTO);
        log.info(JSON.toJSONString(routeDTO));
    }

    @Test
    public void getRouteRunningDetail() {
        RouteDetailDTO routeDTO = routeService.getRouteRunningDetail("235", "60264", "66201706090919305110");
        Assert.assertNotNull(routeDTO);
        log.info(JSON.toJSONString(routeDTO));
    }

    @Test
    public void getRouteVO() {
        RouteVO routeVO = routeService.getRouteVO("235", "60264");
        Assert.assertNotNull(routeVO);
        log.info(JSON.toJSONString(routeVO));
    }
}
