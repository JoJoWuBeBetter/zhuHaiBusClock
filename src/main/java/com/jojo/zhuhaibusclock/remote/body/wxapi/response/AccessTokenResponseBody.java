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
public class AccessTokenResponseBody extends BaseWxApiResponseBody {
    /**
     * accessToken
     */
    @JSONField(name = "access_token")
    private String accessToken;
    /**
     * expiresIn
     */
    @JSONField(name = "expires_in")
    private Integer expiresIn;
}
