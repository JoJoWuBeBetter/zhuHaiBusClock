package com.jojo.zhuhaibusclock.model;

import lombok.Data;

/**
 * @author JoJoWu
 */
@Data
public class BaseResponse<T> {
    private Integer code;
    private String message;
    private T data;

    public BaseResponse(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
