package com.jojo.zhuhaibusclock.quartz.service;

import com.jojo.zhuhaibusclock.model.SysClock;

/**
 * @author JoJoWu
 */
public interface QuartzService {

    void addClockSchedule(SysClock clock);

    void deleteClockSchedule(Long clockId);

}
