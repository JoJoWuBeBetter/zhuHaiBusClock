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
     * accessToken: 小程序全局唯一后台接口调用凭据
     */
    @JSONField(name = "access_token")
    private String accessToken;

    /**
     * expiresIn: 凭证有效时间，单位：秒。目前是7200秒之内的值。
     */
    @JSONField(name = "expires_in")
    private String expiresIn;

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
