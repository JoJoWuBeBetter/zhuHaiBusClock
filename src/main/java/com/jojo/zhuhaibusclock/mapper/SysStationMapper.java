package com.jojo.zhuhaibusclock.mapper;

import com.jojo.zhuhaibusclock.model.SysStation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysStationMapper {
    int deleteByPrimaryKey(String stationId);

    int insert(SysStation record);

    SysStation selectByPrimaryKey(String stationId);

    int updateByPrimaryKeySelective(SysStation record);

    int updateByPrimaryKey(SysStation record);
}