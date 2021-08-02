package com.jojo.zhuhaibusclock.service;


import com.jojo.zhuhaibusclock.model.result.StationSegmentListResult;
import com.jojo.zhuhaibusclock.model.result.RouteRunningDetailResult;
import com.jojo.zhuhaibusclock.model.result.SearchBusByKeywordResult;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author JoJoWu
 */
public interface ZhuHaiBusService {
    SearchBusByKeywordResult searchBusByKeyword(int type, String keyword);

    RouteRunningDetailResult getRouteRunningDetail(String routeId, String segmentId);

    StationSegmentListResult getStationSegmentList(String stationId);
}
