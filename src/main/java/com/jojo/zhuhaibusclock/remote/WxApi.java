package com.jojo.zhuhaibusclock.remote;

import com.alibaba.fastjson.support.retrofit.Retrofit2ConverterFactory;
import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import com.jojo.zhuhaibusclock.remote.body.wxapi.request.SubscribeMessageRequestBody;
import com.jojo.zhuhaibusclock.remote.body.wxapi.response.AccessTokenResponseBody;
import com.jojo.zhuhaibusclock.remote.body.wxapi.response.SessionResponseBody;
import com.jojo.zhuhaibusclock.remote.body.wxapi.response.SubscribeMessageResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * @author JoJoWu
 */
@RetrofitClient(baseUrl = "https://api.weixin.qq.com/", converterFactories = Retrofit2ConverterFactory.class)
public interface WxApi {
    /**
     * 通过小程序前端 wx.login 获得 code 请求session_key
     *
     * @param appId     小程序 appId
     * @param appSecret 小程序 appSecret
     * @param jsCode    小程序登录时获取的 code
     * @param grantType 授权类型，此处只需填写 authorization_code
     * @return 返回的 JSON 数据包
     */
    @GET("sns/jscode2session")
    Call<SessionResponseBody> jsCodeToSession(@Query("appid") String appId, @Query("secret") String appSecret, @Query("js_code") String jsCode, @Query("grant_type") String grantType);

    /**
     * 获取小程序全局唯一后台接口调用凭据（access_token）
     *
     * @param appId     小程序唯一凭证，即 AppID，可在「微信公众平台 - 设置 - 开发设置」页中获得。（需要已经成为开发者，且帐号没有异常状态）
     * @param appSecret 小程序唯一凭证密钥，即 AppSecret，获取方式同 appid
     * @param grantType 填写 client_credential
     * @return 查询结果
     */
    @GET("cgi-bin/token")
    Call<AccessTokenResponseBody> getAccessToken(@Query("appid") String appId, @Query("secret") String appSecret, @Query("grant_type") String grantType);

    @POST("cgi-bin/message/subscribe/send")
    Call<SubscribeMessageResponseBody> sendSubscribeMessage(@Query("access_token") String accessToken, @Body SubscribeMessageRequestBody requestBody);
}
