package com.copilot.modules.security.handle;

import cn.hutool.http.HttpStatus;
import com.copilot.common.utils.R;
import com.copilot.modules.security.utils.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * @program: copilot-server
 * @description: 用于处理未经过身份验证的用户请求受保护资源时触发的异常。它的作用是将用户重定向到登录页面或向用户返回未经授权的错误消息。
 * @author: hfli8
 * @create: 2023/4/13 19:09
 */
@Slf4j
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        log.error("请求访问: " + request.getRequestURI() + " 接口， 经jwt认证失败，无法访问系统资源.");
        SecurityUtil.writeJavaScript(R.error(HttpStatus.HTTP_UNAUTHORIZED, "请求访问:" + request.getRequestURI() + "接口,经jwt 认证失败,无法访问系统资源"), response);
    }
}
