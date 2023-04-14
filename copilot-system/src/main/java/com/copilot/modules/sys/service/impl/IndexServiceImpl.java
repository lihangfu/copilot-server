package com.copilot.modules.sys.service.impl;

import com.copilot.modules.security.bean.SecurityUser;
import com.copilot.modules.security.utils.JwtUtil;
import com.copilot.modules.sys.service.IIndexService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: copilot-server
 * @description: 平台基础服务
 * @author: hfli8
 * @create: 2023/4/14 11:11
 */
@Service
public class IndexServiceImpl implements IIndexService {
    @Resource
    private AuthenticationManager authenticationManager;

    @Override
    public String login(String username, String password) {
        //用户验证
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        //存储认证信息
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //生成token
        SecurityUser userDetail = (SecurityUser) authentication.getPrincipal();
        return JwtUtil.generateToken(userDetail);
    }
}
