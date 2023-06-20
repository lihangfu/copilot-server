package com.copilot.service.security.service;

import cn.hutool.core.util.ObjectUtil;
import com.copilot.service.security.bean.SecurityUser;
import com.copilot.service.user.repository.entity.UserDO;
import com.copilot.service.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * @program: copilot-server
 * @description: 用户身份验证
 * @author: hfli8
 * @create: 2023/4/11 14:19
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserService userService;

    /**
     * 用户名密码登录
     *
     * @param username 用户名
     * @return UserDetails
     * @throws UsernameNotFoundException 用户不存在
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDO sysUser = new UserDO();
        sysUser.setUserName(username);
        UserDO user = userService.findSecurityUserByUser(sysUser);
        if (ObjectUtil.isNull(user)) {
            log.info("登录用户：" + username + " 不存在.");
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        }
        Collection<? extends GrantedAuthority> authorities = getUserAuthorities(user.getId());
        return new SecurityUser(user.getId(), username, user.getPassword(), authorities);
    }

    /**
     * 封装 根据用户Id获取权限
     *
     * @param userId 用户id
     * @return 权限集合
     */
    private Collection<? extends GrantedAuthority> getUserAuthorities(long userId) {
        log.info("{}", userId);
        // 获取用户拥有的角色
        // 用户权限列表，根据用户拥有的权限标识与如 @PreAuthorize("hasAuthority('sys:menu:view')") 标注的接口对比，决定是否可以调用接口
        // 权限集合
        // Set<String> permissions = userService.findPermsByUserId(userId);
        // 角色集合
        // Set<String> roleIds = userService.findRoleIdByUserId(userId);
        // permissions.addAll(roleIds);
        return AuthorityUtils.createAuthorityList("admin");
    }
}
