package com.jojo.zhuhaibusclock.quartz.service;

import com.alibaba.fastjson.JSON;
import com.jojo.zhuhaibusclock.exception.ClockException;
import com.jojo.zhuhaibusclock.model.SysClock;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Service;
// TODO 0/30 0-2 10 ? * 1,2,3,4,5

/**
 * @author JoJoWu
 */
@Slf4j
@Service
public class QuartzServiceImpl implements QuartzService {
    private final Scheduler scheduler;
    private final JobDetail jobDetail;

    public QuartzServiceImpl(Scheduler scheduler, JobDetail jobDetail) {
        this.scheduler = scheduler;
        this.jobDetail = jobDetail;
    }

    @Override
    public void addClockSchedule(SysClock clock) {
        log.info("添加闹钟计划");
        CronTrigger clockTrigger = TriggerBuilder.newTrigger().forJob(jobDetail)
                .withIdentity(clock.getId().toString(), "clockTrigger")
                .usingJobData("clockId", clock.getId())
                .withSchedule(CronScheduleBuilder.cronSchedule("0/1 * * * * ?").withMisfireHandlingInstructionIgnoreMisfires()
                )
                .build();

        try {
            scheduler.scheduleJob(clockTrigger);
        } catch (SchedulerException e) {
            log.error("闹钟任务创建失败:" + e.getMessage());
            log.error("任务数据:" + JSON.toJSONString(clock));
            throw new ClockException("闹钟任务创建失败:" + e.getMessage());
        }
    }

//    @Override
//    public void pauseClockSchedule(Long clockId) {
//        log.info("暂停闹钟计划");
//        TriggerKey triggerKey = TriggerKey.triggerKey(clockId.toString(), "clockTrigger");
//        try {
//            scheduler.pauseTrigger(triggerKey);
//        } catch (SchedulerException e) {
//            log.error("闹钟" + JSON.toJSONString(clockId) + "暂停失败");
//            throw new ClockException("闹钟任务暂停失败");
//        }
//    }

    @Override
    public void deleteClockSchedule(Long clockId) {
        log.info("删除闹钟计划");
        TriggerKey triggerKey = TriggerKey.triggerKey(clockId.toString(), "clockTrigger");
        try {
            scheduler.pauseTrigger(triggerKey);
            scheduler.unscheduleJob(triggerKey);
        } catch (SchedulerException e) {
            log.error("闹钟" + clockId + "删除失败:" + e.getMessage());
            throw new ClockException("闹钟" + clockId + "删除失败:" + e.getMessage());
        }
    }

}
