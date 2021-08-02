package com.jojo.zhuhaibusclock.service;

import com.jojo.zhuhaibusclock.model.SysSegment;
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
public class SegmentServiceTest {
    @Autowired
    SegmentService segmentService;

    @Test
    public void findSegmentTest() {
        SysSegment segment = segmentService.findSegment(2685L, 205L);
        Assert.assertNotNull(segment);
        Assert.assertEquals(segment.getRouteName(), "25路");
    }

    @Test
    public void deleteSegment() {
        Assert.assertNotNull(segmentService.findSegment(2685L, 205L));
        segmentService.deleteSegment(2685L, 205L);
    }
}
