package com.jojo.zhuhaibusclock.mapper;

import com.jojo.zhuhaibusclock.model.SysClock;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author JoJoWu
 */
@Mapper
public interface SysClockMapper {
    int deleteById(Long id);

    int insert(SysClock clock);

    int insertSelective(SysClock clock);

    SysClock selectById(Long id);

    SysClock selectClockAndUserById(Long id);

    List<SysClock> selectClockByUserId(Long userId);

    int updateByIdSelective(SysClock record);

    int updateById(SysClock record);

}