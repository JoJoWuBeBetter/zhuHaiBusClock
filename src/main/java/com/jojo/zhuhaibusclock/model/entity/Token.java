package com.jojo.zhuhaibusclock.model.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JoJoWu
 */
@NoArgsConstructor
@Data
public class Token {
    /**
     * Token
     */
    @JSONField(name = "token")
    private String token;

    @JSONField(name = "userId")
    private Long userId;
}
