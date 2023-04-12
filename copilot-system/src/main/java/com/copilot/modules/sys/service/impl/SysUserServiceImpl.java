package com.copilot.modules.sys.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.copilot.modules.security.bean.SecurityUser;
import com.copilot.modules.security.utils.JwtUtil;
import com.copilot.modules.sys.domain.SysUser;
import com.copilot.modules.sys.mapper.SysUserMapper;
import com.copilot.modules.sys.service.ISysUserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: copilot-server
 * @description: 用户服务
 * @author: hfli8
 * @create: 2023/4/11 14:29
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Override
    public SysUser findByUserInfoName(String username) {
        return baseMapper.selectOne(Wrappers.<SysUser>lambdaQuery().select(SysUser::getId, SysUser::getName, SysUser::getUsername, SysUser::getPassword, SysUser::getGender, SysUser::getAvatar, SysUser::getEmail, SysUser::getMobile, SysUser::getStatus).eq(SysUser::getUsername, username));
    }

    @Override
    public SysUser findSecurityUserByUser(SysUser sysUser) {
        LambdaQueryWrapper<SysUser> select = Wrappers.<SysUser>lambdaQuery().select(SysUser::getId, SysUser::getUsername, SysUser::getPassword);
        if (StrUtil.isNotEmpty(sysUser.getUsername())) {
            select.eq(SysUser::getUsername, sysUser.getUsername());
        } else if (StrUtil.isNotEmpty(sysUser.getMobile())) {
            select.eq(SysUser::getMobile, sysUser.getMobile());
        } else if (ObjectUtil.isNotNull(sysUser.getId()) && sysUser.getId() != 0) {
            select.eq(SysUser::getId, sysUser.getId());
        }
        return baseMapper.selectOne(select);
    }

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
