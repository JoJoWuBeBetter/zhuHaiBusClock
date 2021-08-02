package com.jojo.zhuhaibusclock.quartz;

import com.jojo.zhuhaibusclock.model.SysClock;
import com.jojo.zhuhaibusclock.service.ClockService;
import com.jojo.zhuhaibusclock.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author JoJoWu
 */
@Component
@Slf4j
public class ClockJob extends QuartzJobBean {
    ClockService clockService;
    MessageService messageService;

    public ClockJob(ClockService clockService) {
        this.clockService = clockService;
    }


    @Override
    protected void executeInternal(JobExecutionContext context) {
        JobDataMap jobDataMap = context.getMergedJobDataMap();
        SysClock clock = clockService.getClock(jobDataMap.getLong("clockId"));
//        messageService.pushMessage();
        log.info(clock.toString());
//        System.out.println("ClockJob<" + jobDataMap.getLong("clockId") + ">:" + new Date());
    }
}
