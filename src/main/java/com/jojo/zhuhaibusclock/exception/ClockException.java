package com.jojo.zhuhaibusclock.exception;

import org.springframework.http.HttpStatus;


/**
 * @author JoJoWu
 */
public class ClockException extends AbstractException {
    public ClockException(String message) {
        super(message);
    }

    public ClockException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Http status code
     *
     * @return {@link HttpStatus}
     */
    @Override
    public HttpStatus getStatus() {
        return HttpStatus.CONFLICT;
    }
}
