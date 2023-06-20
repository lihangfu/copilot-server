package com.copilot.web.test;

import com.copilot.api.model.utils.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: copilot-server
 * @description: TODO
 * @author: hfli8
 * @create: 2023/6/20 17:11
 */
@RestController
public class TestController {
    @GetMapping("/test")
    private R<String> test() {
        return R.ok("success");
    }

    @GetMapping("/test/login")
    private R<String> testLogin() {
        return R.ok("login success");
    }
}
