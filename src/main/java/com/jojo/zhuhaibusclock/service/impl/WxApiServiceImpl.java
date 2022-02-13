package com.jojo.zhuhaibusclock.service.impl;

import com.jojo.zhuhaibusclock.config.ZhuHaiBusClockProps;
import com.jojo.zhuhaibusclock.exception.WxApiRequestException;
import com.jojo.zhuhaibusclock.remote.WxApi;
import com.jojo.zhuhaibusclock.remote.body.wxapi.request.SubscribeMessageRequestBody;
import com.jojo.zhuhaibusclock.remote.body.wxapi.response.AccessTokenResponseBody;
import com.jojo.zhuhaibusclock.remote.body.wxapi.response.SessionResponseBody;
import com.jojo.zhuhaibusclock.remote.body.wxapi.response.SubscribeMessageResponseBody;
import com.jojo.zhuhaibusclock.service.WxApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author JoJoWu
 */
@Slf4j
@Service
public class WxApiServiceImpl implements WxApiService {
    private final WxApi wxApi;
    private final ZhuHaiBusClockProps props;

    public WxApiServiceImpl(WxApi wxApi, ZhuHaiBusClockProps props) {
        this.wxApi = wxApi;
        this.props = props;
    }


    @Override
    public SessionResponseBody jsCodeToSession(String jsCode) {
        try {
            return wxApi.jsCodeToSession(
                    props.getWxAppId(), props.getWxAppSecret(), jsCode, "authorization_code").execute().body();
        } catch (IOException e) {
            throw new WxApiRequestException(e.getMessage());
        }
    }

    @Override
    @Cacheable(cacheNames = "access_token")
    public String getAccessToken() {
        try {
            AccessTokenResponseBody body = wxApi.getAccessToken(props.getWxAppId(), props.getWxAppSecret(), "client_credential").execute().body();
            if (body == null) {
                throw new WxApiRequestException("AccessToken请求为空");
            }
            return body.getAccessToken();
        } catch (IOException e) {
            throw new WxApiRequestException(e.getMessage());
        }
    }

    @Override
    public void sendSubscribeMessage(SubscribeMessageRequestBody requestBody) {
        try {
            log.debug("发送微信订阅信息");
            SubscribeMessageResponseBody responseBody = wxApi.sendSubscribeMessage(getAccessToken(), requestBody).execute().body();
            if (responseBody == null) {
                throw new WxApiRequestException("发送订阅消息，请求失败");
            }
            if (responseBody.getErrcode() != 0) {
                throw new WxApiRequestException("调用端口报错:" + responseBody.getErrmsg());
            }
        } catch (IOException e) {
            throw new WxApiRequestException(e.getMessage());
        }
        log.debug("发送成功");
    }
}
