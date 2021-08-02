package com.jojo.zhuhaibusclock.remote;

import com.alibaba.fastjson.support.retrofit.Retrofit2ConverterFactory;
import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author JoJoWu
 */
@RetrofitClient(baseUrl = "https://api.weixin.qq.com/sns/", converterFactories = Retrofit2ConverterFactory.class)
public interface WxApi {
    /**
     * 通过小程序前端 wx.login 获得 code 请求session_key
     *
     * @param appId     小程序 appId
     * @param appSecret 小程序 appSecret
     * @param jsCode    小程序登录时获取的 code
     * @param grantType 授权类型，此处只需填写 authorization_code
     * @return 查询结果
     */
    @GET("jscode2session")
    Call<WxApiResponseBody> jsCodeToSession(@Query("appid") String appId, @Query("secret") String appSecret, @Query("js_code") String jsCode, @Query("grant_type") String grantType);
}
