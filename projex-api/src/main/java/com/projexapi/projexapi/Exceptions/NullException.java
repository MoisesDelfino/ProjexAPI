package com.projexapi.projexapi.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NullException extends RuntimeException{
    public NullException() {
        super();
    }

    public NullException(final String message) {
        super(message);
    }
    public NullException(final String message, final Throwable cause) {
        super(message, cause);
    }
    public NullException(final Throwable cause) {
        super(cause);
    }
}
