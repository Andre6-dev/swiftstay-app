package com.devandre.swiftstay.exception.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * andre on 9/01/2024
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class ApiValidationError extends ApiSubError {

    private String object;
    private String field;
    private Object rejectedValue;
    private String message;

    public ApiValidationError(String object, String message) {
        this.object = object;
        this.message = message;
    }

}
