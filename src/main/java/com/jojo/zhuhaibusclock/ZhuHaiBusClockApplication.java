package com.jojo.zhuhaibusclock;

import com.google.gson.*;
import com.jojo.zhuhaibusclock.config.ZhuHaiBusClockProps;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import retrofit2.converter.gson.GsonConverterFactory;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * @author JoJoWu
 */
@SpringBootApplication
@EnableConfigurationProperties(ZhuHaiBusClockProps.class)
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
