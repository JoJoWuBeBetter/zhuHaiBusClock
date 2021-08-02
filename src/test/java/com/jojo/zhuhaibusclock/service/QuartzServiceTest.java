package com.jojo.zhuhaibusclock.service;

import com.jojo.zhuhaibusclock.model.SysClock;
import com.jojo.zhuhaibusclock.quartz.service.QuartzService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class QuartzServiceTest {
    @Autowired
    QuartzService quartzService;

    @Test
    public void addClockScheduleTest() {
        SysClock clock = new SysClock();
        clock.setId(9L);
        quartzService.addClockSchedule(clock);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        } finally {
            quartzService.deleteClockSchedule(clock.getId());
        }
    }
}
