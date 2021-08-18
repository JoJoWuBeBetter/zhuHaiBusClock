package com.jojo.zhuhaibusclock.service.impl;

import com.alibaba.fastjson.JSON;
import com.jojo.zhuhaibusclock.exception.NotFoundException;
import com.jojo.zhuhaibusclock.exception.SeverErrorException;
import com.jojo.zhuhaibusclock.mapper.SysSegmentMapper;
import com.jojo.zhuhaibusclock.mapper.SysStationMapper;
import com.jojo.zhuhaibusclock.model.SysStation;
import com.jojo.zhuhaibusclock.model.result.RealtimeInfoListResult;
import com.jojo.zhuhaibusclock.model.result.RouteRunningDetailResult;
import com.jojo.zhuhaibusclock.model.result.SearchBusByKeywordResult;
import com.jojo.zhuhaibusclock.model.result.StationSegmentListResult;
import com.jojo.zhuhaibusclock.remote.ZhuHaiBusApi;
import com.jojo.zhuhaibusclock.remote.ZhuHaiBusResponseBody;
import com.jojo.zhuhaibusclock.service.ZhuHaiBusService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static com.jojo.zhuhaibusclock.util.Crypt.decrypt;

/**
 * @author JoJoWu
 */
@Slf4j
@Service
public class ZhuHaiBusServiceImpl implements ZhuHaiBusService {
    private final ZhuHaiBusApi busApi;
    private final SysStationMapper stationMapper;
    private final SysSegmentMapper segmentMapper;

    public ZhuHaiBusServiceImpl(ZhuHaiBusApi busApi, SysStationMapper stationMapper, SysSegmentMapper segmentMapper) {
        this.busApi = busApi;
        this.stationMapper = stationMapper;
        this.segmentMapper = segmentMapper;
    }

    /**
     * 通过关键词查询
     *
     * @param type    查询类型
     * @param keyword 关键词
     * @return 查询结果
     */
    @Override
    public SearchBusByKeywordResult searchBusByKeyword(@NonNull int type, @NonNull String keyword) {
        ZhuHaiBusResponseBody responseBody;
        try {
            responseBody = busApi.searchBusByKeyword(type, keyword).execute().body();
        } catch (IOException e) {
            throw new NotFoundException("查询公交失败");
        }
        if (responseBody == null) {
            throw new NotFoundException("查询公交失败");
        }
        return getResult(responseBody, SearchBusByKeywordResult.class);
    }

    /**
     * 查询路线运行情况
     *
     * @param routeId   路线ID
     * @param segmentId 空间ID（具体不知道公交那边的作用）
     * @return 查询结果
     */
    @Override
    public RouteRunningDetailResult getRouteRunningDetail(String routeId, String segmentId) {
        ZhuHaiBusResponseBody responseBody;
        try {
            responseBody = busApi.getRouteRunningDetail(routeId, segmentId).execute().body();
        } catch (IOException e) {
            throw new NotFoundException("道路运行详情查询失败");
        }
        return responseBodyToRouteRunningDetailResult(responseBody);
    }

    /**
     * 查询指定车站的路线运行情况
     *
     * @param routeId   路线ID
     * @param segmentId 空间ID
     * @param stationId 车站ID
     * @return 查询结果
     */
    @Override
    public RouteRunningDetailResult getRouteRunningDetail(String routeId, String segmentId, String stationId) {
        ZhuHaiBusResponseBody responseBody;
        try {
            responseBody = busApi.getRouteRunningDetail(routeId, segmentId, stationId).execute().body();
        } catch (IOException e) {
            throw new NotFoundException("道路运行详情查询失败");
        }
        return responseBodyToRouteRunningDetailResult(responseBody);
    }

    /**
     * 把responseBody转换为RouteRunningDetailResult（道路详情结果）
     *
     * @param responseBody 请求响应本体
     * @return 结果
     */
    private RouteRunningDetailResult responseBodyToRouteRunningDetailResult(ZhuHaiBusResponseBody responseBody) {
        if (responseBody == null) {
            throw new NotFoundException("道路运行详情查询失败");
        }
        RouteRunningDetailResult result = getResult(responseBody, RouteRunningDetailResult.class);
        result.getStations().forEach(station -> {
            if (stationMapper.selectByPrimaryKey(station.getStationId()) == null) {
                SysStation sysStation = new SysStation();
                BeanUtils.copyProperties(station, sysStation);
                stationMapper.insert(sysStation);
            }
        });
        return result;
    }


    /**
     * @param stationId 车站ID
     * @return 查询结果
     */
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public StationSegmentListResult getStationSegmentList(String stationId) {
        ZhuHaiBusResponseBody responseBody;
        try {
            responseBody = busApi.getStationSegmentList(stationId).execute().body();
        } catch (IOException e) {
            throw new NotFoundException("车站详情查询失败");
        }
        if (responseBody == null) {
            throw new NotFoundException("车站详情查询失败");
        }
        return getResult(responseBody, StationSegmentListResult.class);
    }

    @Override
    public RealtimeInfoListResult getRealtimeInfoList(String stationId, String routeId) {
        ZhuHaiBusResponseBody responseBody;
        try {
            responseBody = busApi.getRealtimeInfoList(stationId, routeId).execute().body();
        } catch (IOException e) {
            throw new NotFoundException("车站详情查询失败");
        }
        if (responseBody == null) {
            throw new NotFoundException("车站详情查询失败");
        }
        return getResult(responseBody, RealtimeInfoListResult.class);
    }

    /**
     * 通过responseBody转换为指定class的实例
     *
     * @param responseBody 响应本体
     * @param classOfT     指定的class
     * @param <T>          指定的class类型
     * @return 指定class的实例
     */
    private <T> T getResult(ZhuHaiBusResponseBody responseBody, Class<T> classOfT) {
        String result;
        try {
            result = decrypt(responseBody.getResult().getResult(), responseBody.getResult().getKey());
        } catch (NoSuchPaddingException | NoSuchAlgorithmException e) {
            log.error("没有匹配的算法");
            throw new SeverErrorException("算法解密错误");
        } catch (InvalidAlgorithmParameterException e) {
            log.error("错误的算法参数");
            throw new SeverErrorException("算法解密错误");
        } catch (InvalidKeyException e) {
            log.error("错误的Key");
            throw new SeverErrorException("算法解密错误");
        } catch (IllegalBlockSizeException e) {
            log.error("错误的BlockSize");
            throw new SeverErrorException("算法解密错误");
        } catch (BadPaddingException e) {
            log.error("错误的BadPadding");
            throw new SeverErrorException("算法解密错误");
        }
        log.info(JSON.toJSONString(result));
        return JSON.parseObject(result, classOfT);
    }

}
