package com.copilot.modules.sys.controller;

import com.copilot.common.utils.R;
import com.copilot.modules.sys.service.IIndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: copilot-server
 * @description: 基础接口
 * @author: hfli8
 * @create: 2023/4/12 14:17
 */
@Slf4j
@RestController
public class IndexController {

    @Resource
    private IIndexService indexService;

    @GetMapping("/test")
    private R<String> test() {
        return R.ok("success");
    }

    @GetMapping("/test/login")
    private R<String> testLogin() {
        return R.ok("login success");
    }
}
