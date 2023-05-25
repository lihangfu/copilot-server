package com.copilot.modules.security.config;

import com.copilot.modules.security.filter.CustomUsernamePasswordAuthenticationFilter;
import com.copilot.modules.security.filter.JwtAuthenticationTokenFilter;
import com.copilot.modules.security.handle.AuthenticationEntryPointImpl;
import com.copilot.modules.security.handle.CustomAuthenticationSuccessHandler;
import com.copilot.modules.security.handle.CustomDaoAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * @program: copilot-server
 * @description: 认证授权框架配置
 * @author: hfli8
 * @create: 2023/4/10 17:09
 */
@Configuration
// 开启 Spring Security
@EnableWebSecurity
public class SecurityConfig {

    @Resource
    UserDetailsService userDetailsService;

    @Resource
    CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Resource
    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    /**
     * 配置密码管理器
     *
     * @return PasswordEncoder
     */
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, CustomUsernamePasswordAuthenticationFilter filter) throws Exception {
        http
                // 基于 token，不需要 csrf
                .csrf(AbstractHttpConfigurer::disable)
                // 基于 token，不需要 session
                .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // 自定义登录过滤器
                .addFilterAt(filter, UsernamePasswordAuthenticationFilter.class)
                // 在登录之前进行 jwt 校验
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                // 权限管理
                .authorizeRequests(registry -> registry.antMatchers("/test", "/login").denyAll().anyRequest().authenticated())
                // 异常处理
                .exceptionHandling(configurer -> configurer
                        // 权限认证失败
                        .authenticationEntryPoint(new AuthenticationEntryPointImpl()));
        return http.build();
    }

    /**
     * 自定义身份验证
     *
     * @return 自定义身份验证器
     */
    @Bean
    public CustomDaoAuthenticationProvider daoAuthenticationProvider() {
        CustomDaoAuthenticationProvider provider = new CustomDaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

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
     * 自定义登录请求过滤器
     *
     * @param authenticationManager authenticationManager
     * @return CustomUsernamePasswordAuthenticationFilter
     */
    @Bean
    public CustomUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
        CustomUsernamePasswordAuthenticationFilter filter = new CustomUsernamePasswordAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManager);
        filter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
        return filter;
    }
}