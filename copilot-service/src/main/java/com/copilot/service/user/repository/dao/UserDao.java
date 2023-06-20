package com.copilot.service.user.repository.dao;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.copilot.service.user.repository.entity.UserDO;
import com.copilot.service.user.repository.mapper.UserMapper;
import org.springframework.stereotype.Repository;

/**
 * @program: copilot-server
 * @description: TODO
 * @author: hfli8
 * @create: 2023/6/20 17:06
 */
@Repository
public class UserDao extends ServiceImpl<UserMapper, UserDO> {

    public UserDO findByUserInfoName(String username) {
        return baseMapper.selectOne(Wrappers.<UserDO>lambdaQuery().select(UserDO::getId, UserDO::getUserName, UserDO::getPassword).eq(UserDO::getUserName, username));
    }

    public UserDO findSecurityUserByUser(UserDO sysUser) {
        LambdaQueryWrapper<UserDO> select = Wrappers.<UserDO>lambdaQuery().select(UserDO::getId, UserDO::getUserName, UserDO::getPassword);
        if (StrUtil.isNotEmpty(sysUser.getUserName())) {
            select.eq(UserDO::getUserName, sysUser.getUserName());
        } else if (ObjectUtil.isNotNull(sysUser.getId()) && sysUser.getId() != 0) {
            select.eq(UserDO::getId, sysUser.getId());
        }
        return baseMapper.selectOne(select);
    }
}
