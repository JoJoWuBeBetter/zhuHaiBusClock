package com.jojo.zhuhaibusclock.service;

import com.jojo.zhuhaibusclock.model.SysStation;
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
public class StationServiceTest {
    @Autowired
    StationService stationService;

    @Test
    public void findStationTest() {
        String stationId = "55181009132739750000";
        SysStation station = stationService.findStation(stationId);
        Assert.assertNotNull(station);
        log.info(station.toString());
        Assert.assertEquals(station.getStationName(), "华发新城");
    }

    @Test
    public void deleteStationTest() {
        String stationId = "55181009132739750000";
        Assert.assertNotNull(stationService.findStation("55181009132739750000"));
        stationService.deleteStation("55181009132739750000");
    }
}
