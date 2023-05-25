package com.copilot.modules.sys.controller;

import com.copilot.common.utils.R;
import com.copilot.modules.security.bean.SecurityUser;
import com.copilot.modules.security.utils.SecurityUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: copilot-server
 * @description: 用户接口
 * @author: hfli8
 * @create: 2023/5/25 14:58
 */
@RestController
@RequestMapping("user")
public class UserController {
    @GetMapping
    public R<SecurityUser> info() {
        return R.ok(SecurityUtil.getUser());
    }
}
