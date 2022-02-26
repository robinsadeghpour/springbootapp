package com.demo.springbootapp.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid Mail")
public class InvalidMailException extends RuntimeException {

    public InvalidMailException() {
    }

    public InvalidMailException(String message) {
        super(message);
    }

    public InvalidMailException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidMailException(Throwable cause) {
        super(cause);
    }

    public InvalidMailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
