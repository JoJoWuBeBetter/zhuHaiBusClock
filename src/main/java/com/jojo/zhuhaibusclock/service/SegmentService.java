package com.jojo.zhuhaibusclock.service;

import com.jojo.zhuhaibusclock.model.SysSegment;

/**
 * @author JoJoWu
 */
public interface SegmentService {
    int addSegment(SysSegment segments);

    SysSegment findSegment(Long segmentId, Long routeId);

    void updateSegment(SysSegment segment);

    void deleteSegment(Long segmentId, Long routeId);
}
