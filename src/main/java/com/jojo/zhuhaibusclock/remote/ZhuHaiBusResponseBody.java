package com.jojo.zhuhaibusclock.remote;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JoJoWu
 */
@NoArgsConstructor
@Data
public class ZhuHaiBusResponseBody {
    /**
     * result
     */
    @JSONField(name = "result")
    private Result result;
    /**
     * message
     */
    @JSONField(name = "message")
    private String message;

    /**
     * Result
     */
    @NoArgsConstructor
    @Data
    public static class Result {
        /**
         * result
         */
        @JSONField(name = "result")
        private String result;
        /**
         * key
         */
        @JSONField(name = "key")
        private String key;
    }
}
