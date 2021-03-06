package com.jojo.zhuhaibusclock.quartz.service;

import com.alibaba.fastjson.JSON;
import com.jojo.zhuhaibusclock.config.ZhuHaiBusClockProps;
import com.jojo.zhuhaibusclock.exception.ClockException;
import com.jojo.zhuhaibusclock.model.SysClock;
import com.jojo.zhuhaibusclock.model.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
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
    private final ZhuHaiBusClockProps props;
    private String FORMAT;
    private Integer REPEAT_TIME;

    public QuartzServiceImpl(Scheduler scheduler, JobDetail jobDetail, ZhuHaiBusClockProps props) {
        this.scheduler = scheduler;
        this.jobDetail = jobDetail;
        this.props = props;
        FORMAT = props.getClockTriggerFormat();
        REPEAT_TIME = props.getClockRepeatTime();
    }

    @Override
    public void addClockSchedule(SysClock clock) {
        log.info("添加闹钟计划");
        List<Trigger> triggers = new ArrayList<>();
        SysUser user = clock.getUser();

        if (clock.getRepeatTime() == null) {
//            判断用户有没有绑定Bark
            triggers.add(getOneTimeTrigger(clock, user.getBarkKey() == null ? 3 : REPEAT_TIME));
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
        log.info("生成CronTriggers");
        List<Trigger> triggers = new ArrayList<>();
        LocalTime alarmTime = clock.getAlarmTime();
        LocalTime alarmTimePlus = alarmTime.plusMinutes(REPEAT_TIME);


        if (alarmTime.getHour() == alarmTimePlus.getHour()) {
            String cronString = String.format("0 %d-%d %d ? * %s",
                    alarmTime.getMinute(), alarmTimePlus.getMinute(), alarmTime.getHour(), clock.getRepeatTime());
            Trigger trigger = TriggerBuilder.newTrigger().forJob(jobDetail)
                    .withIdentity(clock.getId().toString(), String.format(FORMAT, clock.getId()))
                    .usingJobData("clockId", clock.getId())
                    .usingJobData("triggerType", "CronTrigger")
                    .startAt(getStartDate(clock.getAlarmTime()))
                    .withSchedule(CronScheduleBuilder.cronSchedule(cronString).withMisfireHandlingInstructionIgnoreMisfires()
                    )
                    .build();
            log.info("cronTrigger为单段Trigger,cronString:" + cronString);
            triggers.add(trigger);
        } else {
            log.info("cronTrigger跨越小时，为双段Trigger");
            String cronStringFirstPart = String.format("0 %d-59 %d ? * %s",
                    alarmTime.getMinute(), alarmTime.getHour(), clock.getRepeatTime());
            log.info("第一段cronString：" + cronStringFirstPart);
            String cronStringLastPart = String.format("0 0-%d %d ? * %s",
                    alarmTimePlus.getMinute(), alarmTimePlus.getHour(), clock.getRepeatTime());
            log.info("第一段cronString：" + cronStringLastPart);

            triggers.add(TriggerBuilder.newTrigger().forJob(jobDetail)
                    .withIdentity(clock.getId().toString() + "F", String.format(FORMAT, clock.getId()))
                    .usingJobData("clockId", clock.getId())
                    .usingJobData("triggerType", "CronTrigger")
                    .startAt(getStartDate(clock.getAlarmTime()))
                    .withSchedule(CronScheduleBuilder.cronSchedule(cronStringFirstPart).withMisfireHandlingInstructionIgnoreMisfires())
                    .build());

            triggers.add(TriggerBuilder.newTrigger().forJob(jobDetail)
                    .withIdentity(clock.getId().toString() + "L", String.format(FORMAT, clock.getId()))
                    .usingJobData("clockId", clock.getId())
                    .usingJobData("triggerType", "CronTrigger")
                    .startAt(getStartDate(clock.getAlarmTime()))
                    .withSchedule(CronScheduleBuilder.cronSchedule(cronStringLastPart).withMisfireHandlingInstructionIgnoreMisfires())
                    .build());

        }
        log.info("生成CronTriggers完成");
        return triggers;
    }

    private Trigger getOneTimeTrigger(SysClock clock, Integer repeatTime) {
        String groupName = String.format(FORMAT, clock.getId());
        return TriggerBuilder.newTrigger().forJob(jobDetail)
                .withIdentity(clock.getId().toString(), groupName)
                .usingJobData("clockId", clock.getId())
                .usingJobData("triggerType", "SimpleTrigger")
                .startAt(getStartDate(clock.getAlarmTime()))
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(1).withRepeatCount(repeatTime))
                .build();
    }

    private Date getStartDate(LocalTime alarmTime) {
        LocalTime now = LocalTime.now();
        LocalDate nowDate = LocalDate.now();
        if (alarmTime.compareTo(now) <= 0) {
            nowDate = nowDate.plusDays(1);
        }
        return Date.from(alarmTime.atDate(nowDate).atZone(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public void deleteClockSchedule(Long clockId) {
        log.info("删除闹钟计划");
        String groupName = String.format(FORMAT, clockId);
        GroupMatcher<TriggerKey> groupMatcher = GroupMatcher.triggerGroupEquals(groupName);
        try {
            List<TriggerKey> triggerKeys = new ArrayList<>(scheduler.getTriggerKeys(groupMatcher));
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
