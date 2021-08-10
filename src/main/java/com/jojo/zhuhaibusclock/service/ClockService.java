package com.jojo.zhuhaibusclock.service;

import com.jojo.zhuhaibusclock.model.SysClock;
import com.jojo.zhuhaibusclock.model.params.ClockParam;
import com.jojo.zhuhaibusclock.model.vo.ClockVO;

import java.util.List;

/**
 * @author JoJoWu
 */
public interface ClockService {
    /**
     * 添加闹钟
     *
     * @param clockParam 前端传输的闹钟参数
     * @return 闹钟
     */
    SysClock addClock(ClockParam clockParam);

    SysClock updateClock(ClockParam clockParam);

    /**
     * 查找闹钟
     *
     * @param clockId 闹钟ID
     * @return 闹钟
     */
    SysClock getClock(Long clockId);

    /**
     * 获取闹钟VO
     *
     * @param clockId 闹钟ID
     * @return 闹钟
     */
    ClockVO getClockVO(Long clockId);


    SysClock getClockAndUser(Long clockId);

    /**
     * 通过用户ID获取闹钟列表
     *
     * @param userId 用户ID
     * @return 闹钟列表
     */
    List<ClockVO> getClockList(Long userId);

    void goOffClock(Long clockId);

    /**
     * 暂停闹钟
     */
    void pauseClock(Long clockId);

    /**
     * 重启闹钟
     */
    void resumeClock(Long clockId);

    void deleteClock(Long clockId);
}
