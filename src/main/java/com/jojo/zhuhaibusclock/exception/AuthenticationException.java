package com.jojo.zhuhaibusclock.exception;

import org.springframework.http.HttpStatus;

/**
 * @author JoJoWu
 */
public class AuthenticationException extends AbstractException {
    public AuthenticationException(String msg) {
        super(msg);
    }

    /**
     * Http status code
     *
     * @return {@link HttpStatus}
     */
    @Override
    public HttpStatus getStatus() {
        return HttpStatus.UNAUTHORIZED;
    }
}