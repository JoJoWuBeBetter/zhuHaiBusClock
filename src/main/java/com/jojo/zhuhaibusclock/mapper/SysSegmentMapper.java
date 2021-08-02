package com.jojo.zhuhaibusclock.mapper;

import com.jojo.zhuhaibusclock.model.SysSegment;
import com.jojo.zhuhaibusclock.model.SysSegmentKey;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author JoJoWu
 */
@Mapper
public interface SysSegmentMapper {
    int deleteByPrimaryKey(SysSegmentKey key);

    int insert(SysSegment record);

    int insertSelective(SysSegment record);

    SysSegment selectByPrimaryKey(SysSegmentKey key);

    SysSegment selectBySegmentIdAndRouteId(String segmentId, String routeId);

    int updateByPrimaryKeySelective(SysSegment record);

    int updateByPrimaryKey(SysSegment record);
}