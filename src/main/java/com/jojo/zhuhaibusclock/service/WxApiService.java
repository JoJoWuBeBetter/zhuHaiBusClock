package com.jojo.zhuhaibusclock.service;

import com.jojo.zhuhaibusclock.remote.body.wxapi.request.SubscribeMessageRequestBody;
import com.jojo.zhuhaibusclock.remote.body.wxapi.response.AccessTokenResponseBody;
import com.jojo.zhuhaibusclock.remote.body.wxapi.response.SessionResponseBody;
import com.jojo.zhuhaibusclock.remote.body.wxapi.response.SubscribeMessageResponseBody;

/**
 * @author JoJoWu
 */
public interface WxApiService {
    SessionResponseBody jsCodeToSession(String jsCode);

    String getAccessToken();

    void sendSubscribeMessage(SubscribeMessageRequestBody requestBody);
}
