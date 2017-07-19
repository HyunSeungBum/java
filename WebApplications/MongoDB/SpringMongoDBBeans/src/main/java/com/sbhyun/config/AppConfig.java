package com.sbhyun.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.sbhyun.beans.MongoConfigurationBeans;

@Configuration
@ComponentScan("com.sbhyun")
@Import(MongoConfigurationBeans.class)
public class AppConfig {

}
