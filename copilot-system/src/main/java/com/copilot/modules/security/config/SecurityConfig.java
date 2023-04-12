package com.copilot.modules.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collections;

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
        return http.build();
    }

    /**
     * 使用自定义的 userDetailsService 和 passwordEncoder 进行用户认证
     *
     * @return AuthenticationProvider
     */
    @Bean
    public AuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    /**
     * @param authenticationProvider 是AuthenticationManager 的实现
     * @return AuthenticationManager
     * @throws Exception Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationProvider authenticationProvider) throws Exception {
        return new ProviderManager(Collections.singletonList(authenticationProvider));
    }

}