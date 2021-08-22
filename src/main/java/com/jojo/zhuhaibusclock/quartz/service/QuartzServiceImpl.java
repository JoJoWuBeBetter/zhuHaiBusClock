package com.jojo.zhuhaibusclock.quartz.service;

import com.alibaba.fastjson.JSON;
import com.jojo.zhuhaibusclock.exception.ClockException;
import com.jojo.zhuhaibusclock.model.SysClock;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author JoJoWu
 */
@Slf4j
@Service
public class QuartzServiceImpl implements QuartzService {
    private final Scheduler scheduler;
    private final JobDetail jobDetail;
    private final String FORMAT = "clock-%s-Trigger";

    public QuartzServiceImpl(Scheduler scheduler, JobDetail jobDetail) {
        this.scheduler = scheduler;
        this.jobDetail = jobDetail;
    }

    @Override
    public void addClockSchedule(SysClock clock) {
        log.info("添加闹钟计划");
        List<Trigger> triggers = new ArrayList<>();
        if (clock.getRepeatTime() == null) {
            triggers.add(getOneTimeTrigger(clock));
        } else {
            triggers.addAll(getCronTriggers(clock));
        }

        triggers.forEach(t -> {
            try {
                scheduler.scheduleJob(t);
            } catch (SchedulerException e) {
                log.error("闹钟任务创建失败:" + e.getMessage());
                log.error("任务数据:" + JSON.toJSONString(clock));
                throw new ClockException("闹钟任务创建失败:" + e.getMessage());
            }
        });
    }

    private List<Trigger> getCronTriggers(SysClock clock) {
        List<Trigger> triggers = new ArrayList<>();
        LocalTime alarmTime = clock.getAlarmTime();
        LocalTime alarmTimePlus = alarmTime.plusMinutes(10);
        if (alarmTime.getHour() == alarmTimePlus.getHour()) {
            String cronString = String.format("0 %d-%d %d ? * %s",
                    alarmTime.getMinute(), alarmTimePlus.getMinute(), alarmTime.getHour(), clock.getRepeatTime());
            log.info(cronString);
            Trigger trigger = TriggerBuilder.newTrigger().forJob(jobDetail)
                    .withIdentity(clock.getId().toString(), String.format(FORMAT, clock.getId()))
                    .usingJobData("clockId", clock.getId())
                    .withSchedule(CronScheduleBuilder.cronSchedule(cronString).withMisfireHandlingInstructionIgnoreMisfires()
                    )
                    .build();
            triggers.add(trigger);
        } else {
            // TODO  跨小时CronTrigger
        }
        return triggers;
    }

    private Trigger getOneTimeTrigger(SysClock clock) {
        String groupName = String.format(FORMAT, clock.getId());

        LocalTime now = LocalTime.now();
        LocalDate nowDate = LocalDate.now();
        log.info(clock.toString());
        if (clock.getAlarmTime().compareTo(now) <= 0) {
            nowDate = nowDate.plusDays(1);
        }
        Date startDate = Date.from(clock.getAlarmTime().atDate(nowDate).atZone(ZoneId.systemDefault()).toInstant());
        log.info(startDate.toString());


        return TriggerBuilder.newTrigger().forJob(jobDetail)
                .withIdentity(clock.getId().toString(), groupName)
                .usingJobData("clockId", clock.getId())
                .startAt(startDate)
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(1).withRepeatCount(10))
                .build();
    }

    @Override
    public void deleteClockSchedule(Long clockId) {
        log.info("删除闹钟计划");
        String groupName = String.format(FORMAT, clockId);
        GroupMatcher<TriggerKey> groupMatcher = GroupMatcher.triggerGroupEquals(groupName);
        try {
            List<TriggerKey> triggerKeys = new ArrayList<>(scheduler.getTriggerKeys(groupMatcher));
            scheduler.pauseTriggers(groupMatcher);
            scheduler.unscheduleJobs(triggerKeys);
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
