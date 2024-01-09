package com.devandre.swiftstay.exception.common;

import java.io.Serial;

/**
 * andre on 9/01/2024
 */
public class RequestValidationException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public RequestValidationException(String message) {
        super(message);
    }
}
