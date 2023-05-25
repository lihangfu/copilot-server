package com.copilot.modules.security.handle;

import com.copilot.common.utils.R;
import com.copilot.modules.security.bean.SecurityUser;
import com.copilot.modules.security.utils.JwtUtil;
import com.copilot.modules.security.utils.SecurityUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: copilot-server
 * @description: 登录成功后返回 token
 * @author: hfli8
 * @create: 2023/4/19 10:41
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        SecurityUser userDetail = (SecurityUser) authentication.getPrincipal();
        //存储认证信息
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //生成token
        String s = JwtUtil.generateToken(userDetail);
        // 返回token
        SecurityUtil.writeJavaScript(R.ok(s), response);
    }
}
