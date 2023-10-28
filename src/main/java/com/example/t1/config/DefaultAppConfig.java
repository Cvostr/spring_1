package com.example.t1.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.example.t1")
@PropertySource("classpath:application.properties")
public class DefaultAppConfig {
}
