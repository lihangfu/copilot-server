package com.copilot.service.user.repository.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.copilot.api.model.entity.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @program: copilot-server
 * @description: TODO
 * @author: hfli8
 * @create: 2023/6/20 16:26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("user")
public class UserDO extends BaseDO {
    private static final long serialVersionUID = 1L;

    /**
     * 登录方式: 登录方式 0 仅支持账号密码 1 支持三方
     */
    private Integer loginType;

    /**
     * 删除标记
     */
    private Integer deleted;

    /**
     * 登录用户名
     */
    private String userName;

    /**
     * 登录密码，密文存储
     */
    private String password;
}
