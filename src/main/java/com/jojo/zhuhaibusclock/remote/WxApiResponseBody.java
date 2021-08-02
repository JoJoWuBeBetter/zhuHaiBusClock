package com.jojo.zhuhaibusclock.remote;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JoJoWu
 */
@NoArgsConstructor
@Data
public class WxApiResponseBody {
    /**
     * openid
     */
    @JSONField(name = "openid")
    private String openid;
    /**
     * sessionKey
     */
    @JSONField(name = "session_key")
    private String sessionKey;
    /**
     * unionid
     */
    @JSONField(name = "unionid")
    private String unionid;
    /**
     * errcode
     */
    @JSONField(name = "errcode")
    private Integer errcode;
    /**
     * errmsg
     */
    @JSONField(name = "errmsg")
    private String errmsg;
}
