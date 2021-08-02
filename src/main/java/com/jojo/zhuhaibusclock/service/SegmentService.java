package com.jojo.zhuhaibusclock.service;

import com.jojo.zhuhaibusclock.model.SysSegment;

/**
 * @author JoJoWu
 */
public interface SegmentService {
    int addSegment(SysSegment segments);

    SysSegment findSegment(String segmentId, String routeId);

    void updateSegment(SysSegment segment);

    void deleteSegment(String segmentId, String routeId);
}
