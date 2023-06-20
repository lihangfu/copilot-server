package com.copilot.service.user.service.user;

import com.copilot.service.user.repository.dao.UserDao;
import com.copilot.service.user.repository.entity.UserDO;
import com.copilot.service.user.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: copilot-server
 * @description: TODO
 * @author: hfli8
 * @create: 2023/6/20 16:57
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public UserDO findByUserInfoName(String username) {
        return userDao.findByUserInfoName(username);
    }

    @Override
    public UserDO findSecurityUserByUser(UserDO sysUser) {
        return userDao.findSecurityUserByUser(sysUser);
    }
}
