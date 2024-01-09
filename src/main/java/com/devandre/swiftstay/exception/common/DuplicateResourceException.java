package com.devandre.swiftstay.exception.common;

/**
 * andre on 9/01/2024
 */
public class DuplicateResourceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DuplicateResourceException(String message) {
        super(message);
    }
}
