package com.devandre.swiftstay.exception.common;

import java.io.Serial;

/**
 * andre on 9/01/2024
 */
public class DuplicateResourceException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public DuplicateResourceException(String message) {
        super(message);
    }
}
