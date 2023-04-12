package com.copilot.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.copilot.modules.sys.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
}
