package com.jojo.zhuhaibusclock.service;

import com.jojo.zhuhaibusclock.remote.WxApiResponseBody;

import java.io.IOException;

/**
 * @author JoJoWu
 */
public interface WxApiService {
    WxApiResponseBody jsCodeToSession(String jsCode);

    WxApiResponseBody getAccessToken();
}
