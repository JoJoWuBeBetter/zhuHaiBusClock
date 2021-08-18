package com.jojo.zhuhaibusclock.quartz;

import com.jojo.zhuhaibusclock.service.ClockService;
import com.jojo.zhuhaibusclock.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * @author JoJoWu
 */
@Component
@Slf4j
public class ClockJob extends QuartzJobBean {
    private final ClockService clockService;
    private final MessageService messageService;


    public ClockJob(ClockService clockService, MessageService messageService) {
        this.clockService = clockService;
        this.messageService = messageService;

    }


    @Override
    protected void executeInternal(JobExecutionContext context) {
        JobDataMap jobDataMap = context.getMergedJobDataMap();
        clockService.goOffClock(jobDataMap.getLong("clockId"));
//        messageService.pushMessage(clock.getUser().getBarkKey(), JSON.toJSONString(clock.getUser()), JSON.toJSONString(clock));
//        log.info(clock.toString());

    }
}
