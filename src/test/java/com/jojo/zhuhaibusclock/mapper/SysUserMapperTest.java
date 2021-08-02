package com.jojo.zhuhaibusclock.mapper;

import com.jojo.zhuhaibusclock.ZhuHaiBusClockApplication;
import com.jojo.zhuhaibusclock.model.SysUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZhuHaiBusClockApplication.class)
public class SysUserMapperTest {
    @Autowired
    private SysUserMapper userMapper;

    @Test
    public void deleteById() {
    }

    @Test
    public void insert() {
    }

    @Test
    public void insertSelective() {
    }

    @Test
    public void selectById() {
        SysUser user = userMapper.selectById(2L);
        Assert.assertNotNull(user);
    }

    @Test
    public void selectByOpenId() {
    }

    @Test
    public void updateByIdSelective() {
    }

    @Test
    public void updateById() {
    }

    @Test
    public void updateByOpenId() {
    }
}