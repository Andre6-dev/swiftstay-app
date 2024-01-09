package com.devandre.swiftstay.exception.factory;

import com.devandre.swiftstay.exception.core.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * andre on 9/01/2024
 */
@Component
public class ErrorResponseFactory {

    public ResponseEntity<Object> buildResponseEntity(ErrorResponse errorResponse) {
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }

    public ResponseEntity<Object> createErrorResponse(HttpStatus status, Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(status);
        errorResponse.setTitle(ex.getClass().getSimpleName());
        errorResponse.setMessage(ex.getMessage());
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }
}
