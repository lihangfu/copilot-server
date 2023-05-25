package com.copilot.modules.security.bean;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * @program: copilot-server
 * @description: 登录信息存储类
 * @author: hfli8
 * @create: 2023/4/14 16:57
 */
public class UsernamePasswordAuthentication extends UsernamePasswordAuthenticationToken {
    public UsernamePasswordAuthentication(Object principal, Object credentials) {
        super(principal, credentials);
    }
}
