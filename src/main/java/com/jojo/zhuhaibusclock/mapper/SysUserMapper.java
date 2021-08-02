package com.jojo.zhuhaibusclock.mapper;

import com.jojo.zhuhaibusclock.model.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author JoJoWu
 */
@Mapper
public interface SysUserMapper {
    int deleteById(Long id);

    int insert(SysUser user);

    int insertSelective(SysUser user);

    SysUser selectById(Long id);

    SysUser selectByOpenId(String openId);

    int updateByIdSelective(SysUser user);

    int updateById(SysUser user);

    int updateByOpenId(SysUser user);
}