package com.copilot.common.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @program: copilot-server
 * @description: 基础实体类，所有实体都需要继承
 * @author: hfli
 * @create: 2023/4/7 14:40
 */
@Data
public class BaseEntity {
    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 创建者
     */
    @TableField(fill = FieldFill.INSERT)
    private Long  creator;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createDate;
}
