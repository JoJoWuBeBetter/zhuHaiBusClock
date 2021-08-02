package com.jojo.zhuhaibusclock.exception;

import org.springframework.http.HttpStatus;

/**
 * @author JoJoWu
 */
public class WxApiRequestException extends AbstractException {
    public WxApiRequestException(String msg) {
        super(msg);
    }

    /**
     * Http status code
     *
     * @return {@link HttpStatus}
     */
    @Override
    public HttpStatus getStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
