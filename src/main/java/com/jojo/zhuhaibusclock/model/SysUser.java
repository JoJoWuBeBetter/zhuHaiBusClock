package com.jojo.zhuhaibusclock.model;

import java.io.Serializable;

import lombok.Data;

/**
 * sys_user
 *
 * @author
 */
@Data
public class SysUser implements Serializable {
    private Long id;

    /**
     * 用户唯一标识
     */
    private String openId;

    /**
     * 会话密钥
     */
    private String sessionKey;

    /**
     * Bark密钥
     */
    private String barkKey;

    private static final long serialVersionUID = 1L;
}