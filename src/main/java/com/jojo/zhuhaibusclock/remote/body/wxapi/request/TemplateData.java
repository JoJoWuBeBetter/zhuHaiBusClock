package com.jojo.zhuhaibusclock.remote.body.wxapi.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JoJoWu
 */
@NoArgsConstructor
@Data
public class TemplateData {
    /**
     * thing1
     */
    @JSONField(name = "thing1")
    private Title title;
    /**
     * phrase3
     */
    @JSONField(name = "thing4")
    private Body body;

    /**
     * Thing1
     */
    @Data
    public static class Title {
        /**
         * value
         */
        @JSONField(name = "value")
        private String value;

        public Title(String value) {
            this.value = value;
        }
    }

    /**
     * Phrase3
     */
    @Data
    public static class Body {
        /**
         * value
         */
        @JSONField(name = "value")
        private String value;

        public Body(String value) {
            this.value = value;
        }
    }
}
