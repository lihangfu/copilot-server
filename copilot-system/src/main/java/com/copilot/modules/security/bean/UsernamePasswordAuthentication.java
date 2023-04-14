package com.copilot.modules.security.bean;

import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * @program: copilot-server
 * @description: 登录信息存储类
 * @author: hfli8
 * @create: 2023/4/14 16:57
 */
public class UsernamePasswordAuthentication extends UsernamePasswordAuthenticationToken {
    @Getter
    private final Long currentTimestamp;

    public UsernamePasswordAuthentication(Object principal, Object credentials, Long currentTimestamp) {
        super(principal, credentials);
        this.currentTimestamp = currentTimestamp;
    }
}
