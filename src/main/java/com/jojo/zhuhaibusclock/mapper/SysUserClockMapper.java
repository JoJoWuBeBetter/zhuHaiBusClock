package com.jojo.zhuhaibusclock.mapper;

import com.jojo.zhuhaibusclock.model.SysUserClock;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author JoJoWu
 */
@Mapper
public interface SysUserClockMapper {
    int insert(SysUserClock userClock);

    int insertSelective(SysUserClock userClock);
}