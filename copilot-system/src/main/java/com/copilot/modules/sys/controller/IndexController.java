package com.copilot.modules.sys.controller;

import cn.hutool.core.util.StrUtil;
import com.copilot.common.utils.R;
import com.copilot.modules.sys.service.ISysUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @program: copilot-server
 * @description: 基础接口
 * @author: hfli8
 * @create: 2023/4/12 14:17
 */
@RestController
public class IndexController {

    @Resource
    private ISysUserService userService;

    @GetMapping("/test")
    private R<String> test() {
        return R.ok("success");
    }

    @GetMapping("/test/login")
    private R<String> testLogin() {
        return R.ok("login success");
    }


    @RequestMapping("/login")
    public R<String> login(String username, String password, HttpServletRequest request) {
        String token = request.getParameter("token");
        if (StrUtil.isNotEmpty(token)) {
            return R.ok(token);
        }
        return R.ok(userService.login(username, password));
    }
}
