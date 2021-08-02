package com.jojo.zhuhaibusclock.model;

import java.io.Serializable;
import lombok.Data;

/**
 * sys_user_clock
 * @author 
 */
@Data
public class SysUserClock implements Serializable {
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 闹钟ID
     */
    private Long clockId;

    private static final long serialVersionUID = 1L;
}