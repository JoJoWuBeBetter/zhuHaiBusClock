package com.jojo.zhuhaibusclock.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.lianjiatech.retrofit.spring.boot.exception.RetrofitException;
import com.jojo.zhuhaibusclock.exception.SeverErrorException;
import com.jojo.zhuhaibusclock.remote.BarkApi;
import com.jojo.zhuhaibusclock.remote.BarkResponseBody;
import com.jojo.zhuhaibusclock.remote.body.wxapi.request.SubscribeMessageRequestBody;
import com.jojo.zhuhaibusclock.remote.body.wxapi.request.TemplateData;
import com.jojo.zhuhaibusclock.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author JoJoWu
 */
@Service
@Slf4j
public class MessageServiceImpl implements MessageService {
    private final BarkApi barkApi;
    private final WxApiServiceImpl wxApi;

    public MessageServiceImpl(BarkApi api, WxApiServiceImpl wxApi) {
        this.barkApi = api;

        this.wxApi = wxApi;
    }

    @Override
    public void pushMessage(String key, String body) {
        BarkResponseBody responseBody;
        try {
            responseBody = barkApi.pushMessage(key, body).execute().body();
        } catch (IOException | RetrofitException e) {
            log.error(e.getMessage());
            throw new SeverErrorException(e.getMessage());
        }
        checkRequest(responseBody);
    }


    @Override
    public void pushMessage(String key, String title, String body) {
        BarkResponseBody responseBody;
        try {
            responseBody = barkApi.pushMessage(key, title, body).execute().body();
        } catch (IOException | RetrofitException e) {
            log.error(e.getMessage());
            throw new SeverErrorException(e.getMessage());
        }
        checkRequest(responseBody);
    }

    @Override
    public void pushWxMessage(String openId, String title, String body) {
        SubscribeMessageRequestBody requestBody = new SubscribeMessageRequestBody();
        requestBody.setTouser(openId);
        requestBody.setTemplateId("Ajflo4hzcFuEt57eAF5-6wnUCZa86Uh8Gvd64KnEAX8");
        TemplateData data = new TemplateData();
        data.setTitle(new TemplateData.Title(title));
        data.setBody(new TemplateData.Body(body));
        requestBody.setMessageData(data);
        wxApi.sendSubscribeMessage(requestBody);
    }


    private void checkRequest(BarkResponseBody responseBody) {
        if (responseBody == null) {
            log.error("Bark推送消息无响应");
            throw new SeverErrorException("Bark推送消息无响应");
        }
        if (responseBody.getCode() != HttpStatus.OK.value()) {
            log.error(responseBody.getMessage());
            throw new SeverErrorException(responseBody.getMessage());
        }
        log.info("Bark消息推送成功：" + JSON.toJSONString(responseBody));
    }
}
