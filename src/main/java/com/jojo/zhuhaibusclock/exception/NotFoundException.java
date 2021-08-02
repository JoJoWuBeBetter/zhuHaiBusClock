package com.jojo.zhuhaibusclock.exception;

import org.springframework.http.HttpStatus;

/**
 * @author JoJoWu
 */
public class NotFoundException extends AbstractException {
    public NotFoundException(String msg) {
        super(msg);
    }

    /**
     * Http status code
     *
     * @return {@link HttpStatus}
     */
    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}