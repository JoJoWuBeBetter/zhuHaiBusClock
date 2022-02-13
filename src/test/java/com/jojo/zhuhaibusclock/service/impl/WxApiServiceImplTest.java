package com.jojo.zhuhaibusclock.service.impl;

import com.alibaba.fastjson.JSON;
import com.jojo.zhuhaibusclock.remote.body.wxapi.request.SubscribeMessageRequestBody;
import com.jojo.zhuhaibusclock.remote.body.wxapi.request.TemplateData;
import com.jojo.zhuhaibusclock.service.WxApiService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class WxApiServiceImplTest {
    @Autowired
    WxApiService wxApiService;

    @Test
    public void jsCodeToSession() {
    }

    @Test
    public void getAccessToken() {
        log.info(wxApiService.getAccessToken());
    }

    @Test
    public void sendSubscribeMessage() {
        SubscribeMessageRequestBody requestBody = new SubscribeMessageRequestBody();
        requestBody.setTouser("oLK2-5b9x6mrX-igJjyDstH70FgY");
        requestBody.setTemplateId("Ajflo4hzcFuEt57eAF5-6wnUCZa86Uh8Gvd64KnEAX8");
        TemplateData data = new TemplateData();
        data.setTitle(new TemplateData.Title("测试"));
        data.setBody(new TemplateData.Body("测试"));
        requestBody.setMessageData(data);
        log.info(JSON.toJSONString(requestBody));
        wxApiService.sendSubscribeMessage(requestBody);
    }
}