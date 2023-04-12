package com.copilot.modules.security.utils;

import com.copilot.common.exception.BaseException;
import com.copilot.common.utils.R;
import com.copilot.modules.security.bean.SecurityUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @program: copilot-server
 * @description: 安全服务工具类
 * @author: hfli8
 * @create: 2023/4/12 13:54
 */
@UtilityClass
public class SecurityUtil {

    public void writeJavaScript(R r, HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        response.setStatus(200);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(mapper.writeValueAsString(r));
        printWriter.flush();
    }

    /**
     * 获取Authentication
     */
    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取用户
     *
     * @return 用户
     */
    public SecurityUser getUser() {
        try {
            return (SecurityUser) getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new BaseException("登录状态过期", HttpStatus.UNAUTHORIZED.value());
        }
    }
}
