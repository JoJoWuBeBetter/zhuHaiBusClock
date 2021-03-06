package com.jojo.zhuhaibusclock.service.impl;

import com.jojo.zhuhaibusclock.config.ZhuHaiBusClockProps;
import com.jojo.zhuhaibusclock.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MessageServiceImplTest {
    @Autowired
    MessageService msgService;

    @Autowired
    private ZhuHaiBusClockProps props;

    @Test
    public void pushMessage() {
        msgService.pushMessage(props.getTestBarkKey(), "test");
    }

    @Test
    public void pushMessageWithTitle() {
        msgService.pushMessage(props.getTestBarkKey(), "title", "test");
    }
}
