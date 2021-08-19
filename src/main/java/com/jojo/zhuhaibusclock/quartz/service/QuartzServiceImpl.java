package com.jojo.zhuhaibusclock.quartz.service;

import com.alibaba.fastjson.JSON;
import com.jojo.zhuhaibusclock.exception.ClockException;
import com.jojo.zhuhaibusclock.model.SysClock;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
// TODO  重复闹钟使用CronTrigger

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
        Trigger trigger;
        if (clock.getRepeatTime() == null) {
            LocalTime now = LocalTime.now();
            LocalDate nowDate = LocalDate.now();
            log.info(clock.toString());
            if (clock.getAlarmTime().compareTo(now) <= 0) {
                nowDate = nowDate.plusDays(1);
            }

            Date startDate = Date.from(clock.getAlarmTime().atDate(nowDate).atZone(ZoneId.systemDefault()).toInstant());
            log.info(startDate.toString());


            trigger = TriggerBuilder.newTrigger().forJob(jobDetail)
                    .withIdentity(clock.getId().toString(), "clockTrigger")
                    .usingJobData("clockId", clock.getId())
                    .startAt(startDate)
                    .withSchedule(
                            SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(1).withRepeatCount(10))
                    .build();
        } else {
            trigger = TriggerBuilder.newTrigger().forJob(jobDetail)
                    .withIdentity(clock.getId().toString(), "clockTrigger")
                    .usingJobData("clockId", clock.getId())
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/1 * * * * ?").withMisfireHandlingInstructionIgnoreMisfires()
                    )
                    .build();
        }


        try {
            scheduler.scheduleJob(trigger);
        } catch (SchedulerException e) {
            log.error("闹钟任务创建失败:" + e.getMessage());
            log.error("任务数据:" + JSON.toJSONString(clock));
            throw new ClockException("闹钟任务创建失败:" + e.getMessage());
        }
    }

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

    public static String getClockCronString(SysClock clock) {

        String cronString;
        cronString = String.format("0/30 %d-%d %d ? * %s",
                clock.getAlarmTime().plusMinutes(-5).getMinute(),
                clock.getAlarmTime().getMinute(),
                clock.getAlarmTime().getHour(),
                clock.getRepeatTime());

        return cronString;
    }

}
