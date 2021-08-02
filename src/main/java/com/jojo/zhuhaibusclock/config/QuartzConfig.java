package com.jojo.zhuhaibusclock.config;

import com.jojo.zhuhaibusclock.quartz.ClockJob;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author JoJoWu
 */
@Configuration
public class QuartzConfig {
    @Bean
    JobDetail jobDetail() {
        return JobBuilder.newJob(ClockJob.class)
                .withIdentity("clock", "clock")
                .storeDurably()
                .build();
    }
}
