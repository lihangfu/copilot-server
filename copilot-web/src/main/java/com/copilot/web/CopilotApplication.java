package com.copilot.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 系统入口
 */
@ComponentScan(basePackages = {"com.copilot"})
@SpringBootApplication
public class CopilotApplication {

    public static void main(String[] args) {
        SpringApplication.run(CopilotApplication.class, args);
    }

}
