package com.jojo.zhuhaibusclock.service.impl;

import com.jojo.zhuhaibusclock.mapper.SysClockMapper;
import com.jojo.zhuhaibusclock.model.SysClock;
import com.jojo.zhuhaibusclock.quartz.service.QuartzService;
import com.jojo.zhuhaibusclock.quartz.service.QuartzServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class QuartzServiceImplTest {
    @Autowired
    QuartzService quartzService;

    @Resource
    SysClockMapper clockMapper;

    @Test
    public void addClockScheduleTest() {
        SysClock clock = clockMapper.selectById(22L);
        quartzService.addClockSchedule(clock);
        try {
            TimeUnit.SECONDS.sleep(3600);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        } finally {
            quartzService.deleteClockSchedule(clock.getId());
        }
    }

    @Test
    public void deleteClockScheduleTest() {
        SysClock clock = new SysClock();
        clock.setId(22L);
        quartzService.deleteClockSchedule(clock.getId());
    }

    @Test
    public void getClockCronString() {
        SysClock clock = new SysClock();
        LocalTime testTime = LocalTime.of(15, 0);
        clock.setAlarmTime(testTime);
        String s = QuartzServiceImpl.getClockCronString(clock);
        log.info(s);
    }
}
