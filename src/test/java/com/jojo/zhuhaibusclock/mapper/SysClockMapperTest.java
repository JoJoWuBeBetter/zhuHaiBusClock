package com.jojo.zhuhaibusclock.mapper;

import com.jojo.zhuhaibusclock.ZhuHaiBusClockApplication;
import com.jojo.zhuhaibusclock.model.SysClock;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZhuHaiBusClockApplication.class)
public class SysClockMapperTest {
    @Autowired
    private SysClockMapper clockMapper;

    private final Long CLOCK_ID = 22L;

    @Test
    public void selectUserAndClockById() {
        SysClock clock = clockMapper.selectClockAndUserById(CLOCK_ID);
        log.info(clock.toString());
        Assert.assertNotNull(clock);
    }

    @Test
    public void updateById() {
        SysClock clock = clockMapper.selectById(CLOCK_ID);
        Assert.assertNotNull(clock);
        boolean isEnable = clock.getIsEnable();
        clock.setIsEnable(!isEnable);
        clockMapper.updateById(clock);
        clock = clockMapper.selectById(CLOCK_ID);
        Assert.assertNotNull(clock);
        Assert.assertEquals(clock.getIsEnable(), !isEnable);
    }
}
