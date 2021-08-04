package com.jojo.zhuhaibusclock.remote.fallback;

import com.jojo.zhuhaibusclock.remote.ZhuHaiBusApi;

import com.jojo.zhuhaibusclock.remote.ZhuHaiBusResponseBody;
import retrofit2.Call;

/**
 * @author JoJoWu
 */
public abstract class ZhuHaiBusApiFallback implements ZhuHaiBusApi {
    @Override
    public Call<ZhuHaiBusResponseBody> searchBusByKeyword(int type, String keyword) {

        return null;
    }

    @Override
    public Call<ZhuHaiBusResponseBody> getRouteRunningDetail(String routeId, String segmentId) {
        return null;
    }

    @Override
    public Call<ZhuHaiBusResponseBody> getStationSegmentList(String stationId) {
        return null;
    }
}
