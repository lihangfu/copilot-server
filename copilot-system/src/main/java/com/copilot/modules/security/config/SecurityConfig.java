package com.copilot.modules.security.config;

import com.copilot.modules.security.handle.AuthenticationEntryPointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @program: copilot-server
 * @description: TODO
 * @author: hfli8
 * @create: 2023/4/10 17:09
 */
@Configuration
// 开启 Spring Security
@EnableWebSecurity
public class SecurityConfig {

    /**
     * 获取AuthenticationManager（认证管理器），登录时认证使用
     *
     * @param authenticationConfiguration authenticationConfiguration
     * @return authenticationManager
     * @throws Exception Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * 配置密码管理器
     *
     * @return PasswordEncoder
     */
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests(registry -> registry
                        .antMatchers("/test", "/login").anonymous()
                        .anyRequest()
                        .authenticated())
                .sessionManagement(session -> session
                        .maximumSessions(1)
                );
        // 异常处理
        http
                .exceptionHandling(configurer -> configurer
                        .authenticationEntryPoint(new AuthenticationEntryPointImpl()));
        return http.build();
    }
}