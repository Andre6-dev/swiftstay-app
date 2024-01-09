package com.devandre.swiftstay.exception.common;

import java.io.Serial;

/**
 * andre on 9/01/2024
 */
public class InternalServerException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InternalServerException(String message) {
        super(message);
    }
}
