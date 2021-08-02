package com.jojo.zhuhaibusclock.model;

import java.io.Serializable;
import lombok.Data;

/**
 * sys_segment
 * @author 
 */
@Data
public class SysSegmentKey implements Serializable {
    /**
     * 公交行驶路线ID
     */
    private Long segmentId;

    /**
     * 公交路线ID
     */
    private Long routeId;

    private static final long serialVersionUID = 1L;
}