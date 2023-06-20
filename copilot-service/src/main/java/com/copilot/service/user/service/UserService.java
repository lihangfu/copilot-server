package com.copilot.service.user.service;

import com.copilot.service.user.repository.entity.UserDO;

public interface UserService {
    /**
     * 通过用户名查找用户个人信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    UserDO findByUserInfoName(String username);

    /**
     * 通过用户去查找用户(id/用户名/手机号)
     *
     * @param sysUser
     * @return
     */
    UserDO findSecurityUserByUser(UserDO sysUser);
}
