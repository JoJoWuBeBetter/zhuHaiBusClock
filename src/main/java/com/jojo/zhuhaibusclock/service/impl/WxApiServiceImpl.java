package com.jojo.zhuhaibusclock.service.impl;

import com.jojo.zhuhaibusclock.config.ZhuHaiBusClockProps;
import com.jojo.zhuhaibusclock.exception.WxApiRequestException;
import com.jojo.zhuhaibusclock.remote.WxApi;
import com.jojo.zhuhaibusclock.remote.WxApiResponseBody;
import com.jojo.zhuhaibusclock.service.WxApiService;
import lombok.extern.slf4j.Slf4j;
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
    public WxApiResponseBody jsCodeToSession(String jsCode) {
        try {
            return wxApi.jsCodeToSession(
                    props.getWxAppId(), props.getWxAppSecret(), jsCode, "authorization_code").execute().body();
        } catch (IOException e) {
            throw new WxApiRequestException(e.getMessage());
        }
    }

    @Override
    public WxApiResponseBody getAccessToken() {
        try {
            return wxApi.getAccessToken(props.getWxAppId(), props.getWxAppSecret(), "client_credential").execute().body();
        } catch (IOException e) {
            throw new WxApiRequestException(e.getMessage());
        }
    }
}
