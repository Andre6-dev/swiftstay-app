package com.devandre.swiftstay.utils;

/**
 * andre on 9/01/2024
 */
public class Constants {

    // API CONSTANTS
    public static final String API_BASE_PATH = "/api/v1/";

    public static final class Exception {
        public static final class Common {

            public static final String MALFORMED_JSON_REQUEST = "Malformed JSON request";
            public static final String WRITABLE_ERROR = "Error writing JSON output";

            public static final String METHOD_NOT_FOUND= "Could not find the %s method for URL %s";

            public static final String DATABASE_ERROR = "Database error";

            public static final String VALIDATION_MESSAGE = "Validation error. Check 'errors' field for details.";

        }
    }

}
