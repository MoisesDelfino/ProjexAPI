package com.projexapi.projexapi.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotfoundException extends RuntimeException {
    public NotfoundException() {
        super();
    }

    public NotfoundException(final String message) {
        super(message);
    }
    public NotfoundException(final String message, final Throwable cause) {
        super(message, cause);
    }
    public NotfoundException(final Throwable cause) {
        super(cause);
    }
}
