package com.copilot.modules.security.filter;

import com.copilot.modules.security.bean.UsernamePasswordAuthentication;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.wildfly.common.annotation.NotNull;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: copilot-server
 * @description: 自定义登录逻辑
 * @author: hfli8
 * @create: 2023/4/18 19:49
 */
public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Setter
    private boolean postOnly = true;
    @Getter
    private String currentTimestampParameter = "currentTimestamp";

    @NotNull
    protected Long obtainCurrentTimestamp(HttpServletRequest request) {
        return Long.valueOf(request.getParameter(this.currentTimestampParameter));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            String username = this.obtainUsername(request);
            username = username != null ? username.trim() : "";
            String password = this.obtainPassword(request);
            password = password != null ? password : "";
            Long currentTimestamp = this.obtainCurrentTimestamp(request);
            currentTimestamp = currentTimestamp != null ? currentTimestamp : 0;
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthentication(username, password, currentTimestamp);
            this.setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }
}
