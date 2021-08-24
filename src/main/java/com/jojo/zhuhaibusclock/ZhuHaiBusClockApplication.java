package com.jojo.zhuhaibusclock;

import com.jojo.zhuhaibusclock.config.ZhuHaiBusClockProps;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * @author JoJoWu
 */
@SpringBootApplication
@EnableConfigurationProperties(ZhuHaiBusClockProps.class)
@EnableTransactionManagement
public class ZhuHaiBusClockApplication {


    /**
     * 因为GsonConverterFactory没有无参构造器，所以手动注入。
     *
     * @return GsonConverterFactory
     */
    @Bean
    public GsonConverterFactory gsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    public static void main(String[] args) {
        SpringApplication.run(ZhuHaiBusClockApplication.class, args);
    }

}
