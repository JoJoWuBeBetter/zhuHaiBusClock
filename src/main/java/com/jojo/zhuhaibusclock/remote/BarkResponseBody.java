package com.jojo.zhuhaibusclock.remote;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Bark Response Body *
 *
 * @author JoJoWu
 * @apiNote {"code":200,"message":"success","timestamp":1627373082}
 */
@NoArgsConstructor
@Data
public class BarkResponseBody {
    /**
     * code
     */
    @JSONField(name = "code")
    private Integer code;
    /**
     * message
     */
    @JSONField(name = "message")
    private String message;
    /**
     * timestamp
     */
    @JSONField(name = "timestamp")
    private Integer timestamp;
}
