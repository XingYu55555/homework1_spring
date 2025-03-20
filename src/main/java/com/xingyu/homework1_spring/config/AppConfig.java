package com.xingyu.homework1_spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import com.xingyu.homework1_spring.services.KeyValueService;
import com.xingyu.homework1_spring.services.KeyValueServiceImpl;

@Configuration
@ComponentScan(basePackages = "com.xingyu.homework1_spring.services")
public class AppConfig {
    @Bean
    public KeyValueService keyValueService() {
        return new KeyValueServiceImpl();
    }
}
