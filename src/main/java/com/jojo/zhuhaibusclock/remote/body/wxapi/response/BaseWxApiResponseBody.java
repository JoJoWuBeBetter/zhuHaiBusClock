package com.jojo.zhuhaibusclock.remote.body.wxapi.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JoJoWu
 */
@NoArgsConstructor
@Data
public class BaseWxApiResponseBody {

    /**
     * 错误码：
     *  -1	    系统繁忙，此时请开发者稍候再试
     *  0	    请求成功
     *  40003	touser字段openid为空或者不正确
     *  40029	code 无效
     *  40037	订阅模板id为空不正确
     *  40226	高风险等级用户，小程序登录拦截 。风险等级详见用户安全解方案
     *  41030	page路径不正确，需要保证在现网版本小程序中存在，与app.json保持一致
     *  43101	用户拒绝接受消息，如果用户之前曾经订阅过，则表示用户取消了订阅关系
     *  45011	频率限制，每个用户每分钟100次
     *  47003	模板参数不准确，可能为空或者不满足规则，errmsg会提示具体是哪个字段出错
     */
    @JSONField(name = "errcode")
    private Integer errcode;
    /**
     * 错误信息
     */
    @JSONField(name = "errmsg")
    private String errmsg;
}
