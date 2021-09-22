package com.jojo.zhuhaibusclock.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author JoJoWu
 */
@Data
@ConfigurationProperties("zhuhai-bus-clock")
public class ZhuHaiBusClockProps {
    private String wxAppId;
    private String wxAppSecret;
    private String testBarkKey;
    private Integer clockRepeatTime;
    private String clockTriggerFormat;
}
