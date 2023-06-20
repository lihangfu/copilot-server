package com.copilot.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.copilot.service")
@MapperScan(basePackages = {
        "com.copilot.service.user.repository.mapper",})
public class ServiceAutoConfig {
}
