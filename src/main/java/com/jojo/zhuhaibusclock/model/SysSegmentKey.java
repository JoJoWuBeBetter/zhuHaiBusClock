package com.jojo.zhuhaibusclock.model;

import java.io.Serializable;
import lombok.Data;

/**
 * sys_segment
 * @author JoJoWu
 */
@Data
public class SysSegmentKey implements Serializable {
    /**
     * 公交行驶路线ID
     */
    private String segmentId;

    /**
     * 公交路线ID
     */
    private String routeId;

    private static final long serialVersionUID = 1L;
}