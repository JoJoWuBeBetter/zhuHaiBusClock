package com.jojo.zhuhaibusclock.quartz;

import com.jojo.zhuhaibusclock.config.ZhuHaiBusClockProps;
import com.jojo.zhuhaibusclock.service.ClockService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.SimpleTrigger;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * @author JoJoWu
 */
@Component
@Slf4j
public class ClockJob extends QuartzJobBean {
    private final ClockService clockService;
    private final ZhuHaiBusClockProps props;
    private Integer REPEAT_TIME;


    public ClockJob(ClockService clockService, ZhuHaiBusClockProps props) {
        this.clockService = clockService;
        this.props = props;
        REPEAT_TIME = props.getClockRepeatTime();
    }


    @Override
    protected void executeInternal(JobExecutionContext context) {
        JobDataMap jobDataMap = context.getMergedJobDataMap();
        clockService.goOffClock(jobDataMap.getLong("clockId"));
        String simpleTrigger = "SimpleTrigger";
        String triggerType = jobDataMap.getString("triggerType");
        if (simpleTrigger.equals(triggerType)) {
            SimpleTrigger st = (SimpleTrigger) context.getTrigger();
            if (st.getTimesTriggered() == REPEAT_TIME + 1) {
                log.info("不重复闹钟已完成，关闭闹钟");
                clockService.pauseClock(jobDataMap.getLong("clockId"));
            }
        }
    }
}
