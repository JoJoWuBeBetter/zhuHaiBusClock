package com.jojo.zhuhaibusclock.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.lianjiatech.retrofit.spring.boot.exception.RetrofitException;
import com.jojo.zhuhaibusclock.exception.SeverErrorException;
import com.jojo.zhuhaibusclock.remote.BarkApi;
import com.jojo.zhuhaibusclock.remote.BarkResponseBody;
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

    public MessageServiceImpl(BarkApi barkApi) {
        this.barkApi = barkApi;
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
    public void pushMessage(String body) {

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
