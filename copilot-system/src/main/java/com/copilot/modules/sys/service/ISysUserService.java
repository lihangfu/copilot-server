package com.copilot.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.copilot.modules.sys.domain.SysUser;

public interface ISysUserService extends IService<SysUser> {
    /**
     * 通过用户名查找用户个人信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    SysUser findByUserInfoName(String username);

    /**
     * 通过用户去查找用户(id/用户名/手机号)
     *
     * @param sysUser
     * @return
     */
    SysUser findSecurityUserByUser(SysUser sysUser);
}
