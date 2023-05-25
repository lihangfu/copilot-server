package com.copilot.modules.sys.controller;

import com.copilot.common.utils.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: copilot-server
 * @description: 基础接口
 * @author: hfli8
 * @create: 2023/4/12 14:17
 */
@RestController
public class IndexController {

    @GetMapping("/test")
    private R<String> test() {
        return R.ok("success");
    }

    @GetMapping("/test/login")
    private R<String> testLogin() {
        return R.ok("login success");
    }
}
