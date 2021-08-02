package com.jojo.zhuhaibusclock.remote;

import com.alibaba.fastjson.support.retrofit.Retrofit2ConverterFactory;
import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author JoJoWu
 */
@RetrofitClient(baseUrl = "https://api.day.app/", converterFactories = Retrofit2ConverterFactory.class)
public interface BarkApi {
    /**
     * 推送消息
     *
     * @param key  用户专属key
     * @param body 内容
     * @return 推送情况
     */
    @GET("/{key}/{body}")
    Call<BarkResponseBody> pushMessage(@Path("key") String key, @Path("body") String body);

    /**
     * 推送含标题的消息
     *
     * @param key   用户专属key
     * @param title 标题
     * @param body  内容
     * @return 推送情况
     */
    @GET("/{key}/{title}/{body}")
    Call<BarkResponseBody> pushMessage(@Path("key") String key, @Path("title") String title, @Path("body") String body);
}
