package com.jojo.zhuhaibusclock.remote;

import com.alibaba.fastjson.support.retrofit.Retrofit2ConverterFactory;
import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import retrofit2.Call;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * @author JoJoWu
 */
@RetrofitClient(baseUrl = "https://bus.zhbuswx.com/api/bus/mp/", converterFactories = Retrofit2ConverterFactory.class)
public interface ZhuHaiBusApi {
    /**
     * 通过关键字（站名或者公交号）查询相关巴士路线
     *
     * @param type    查询类型
     * @param keyword 查询关键字
     * @return 查询请求
     */
    @Headers({
            "User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36 MicroMessenger/7.0.9.501 NetType/WIFI MiniProgramEnv/Windows WindowsWechat",
            "content-type: application/json",
            "Referer: https://servicewechat.com/wxbe3e13998d884cdd/50/page-frame.html"
    })
    @GET("bussearch")
    Call<ZhuHaiBusResponseBody> searchBusByKeyword(@Query("type") int type, @Query("keyword") String keyword);

    /**
     * 查询公交路线行驶状况
     *
     * @param routeId   公交路线ID
     * @param segmentId 开往终点站ID
     * @return 查询请求
     */
    @Headers({
            "User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36 MicroMessenger/7.0.9.501 NetType/WIFI MiniProgramEnv/Windows WindowsWechat",
            "content-type: application/json",
            "Referer: https://servicewechat.com/wxbe3e13998d884cdd/50/page-frame.html"
    })
    @GET("getRouteRunningDetail")
    Call<ZhuHaiBusResponseBody> getRouteRunningDetail(@Query("routeid") String routeId, @Query("segmentid") String segmentId);

    /**
     * 查询
     * @param stationId
     * @return
     */
    @Headers({
            "User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36 MicroMessenger/7.0.9.501 NetType/WIFI MiniProgramEnv/Windows WindowsWechat",
            "content-type: application/json",
            "Referer: https://servicewechat.com/wxbe3e13998d884cdd/50/page-frame.html"
    })
    @GET("getStationSegmentlist")
    Call<ZhuHaiBusResponseBody> getStationSegmentList(@Query("StationID") String stationId);
}
