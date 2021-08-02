package com.jojo.zhuhaibusclock.exception;

import org.springframework.http.HttpStatus;

/**
 * @author JoJoWu
 */
public class SeverErrorException extends AbstractException{
    public SeverErrorException(String message) {
        super(message);
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
