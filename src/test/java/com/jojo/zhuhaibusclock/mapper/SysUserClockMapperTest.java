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
public class SysUserClockMapperTest {
    @Autowired
    private SysUserClockMapper userClockMapper;

    @Test
    public void deleteByClockId() {
        userClockMapper.deleteByClockId(4L);
    }
}
