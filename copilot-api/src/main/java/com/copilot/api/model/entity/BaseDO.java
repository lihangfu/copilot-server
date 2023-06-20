package com.copilot.api.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: copilot-server
 * @description: 数据库实体基类
 * @author: hfli8
 * @create: 2023/6/20 16:12
 */
@Data
public class BaseDO implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;

    private Date createTime;

    private Date updateTime;
}
