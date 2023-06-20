package com.copilot.service.security.handle;

import com.copilot.api.model.utils.R;
import com.copilot.service.security.utils.SecurityUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: copilot-server
 * @description: 登录失败后返回错误提示
 * @author: hfli8
 * @create: 2023/5/25 11:09
 */
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        SecurityUtil.writeJavaScript(R.error("登录失败，请检查用户名或密码"), response);
    }
}
