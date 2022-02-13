package com.jojo.zhuhaibusclock.remote.body.wxapi.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author JoJoWu
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class SessionResponseBody extends BaseWxApiResponseBody {
    /**
     * 用户唯一标识
     */
    @JSONField(name = "openid")
    private String openid;

    /**
     * 会话密钥
     */
    @JSONField(name = "session_key")
    private String sessionKey;

    /**
     * 用户在开放平台的唯一标识符，若当前小程序已绑定到微信开放平台帐号下会返回，详见 UnionID 机制说明。
     */
    @JSONField(name = "unionid")
    private String unionid;
}
