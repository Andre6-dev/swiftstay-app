package com.devandre.swiftstay.exception.core;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase;

/**
 * andre on 9/01/2024
 */
public class LowerCaseClassNameResolver extends TypeIdResolverBase {

    /**
     ** This method is used to convert the class name to lower case
     */
    @Override
    public String idFromValue(Object value) {
        return value.getClass().getSimpleName().toLowerCase();
    }

    /**
     * This method calls the idFromValue method to
     * generate the identifier from the provided value.
     */
    @Override
    public String idFromValueAndType(Object value, Class<?> suggestedType) {
        return idFromValue(value);
    }

    /**
     * This method returns the mechanism used to
     * generate the identifier.
     */
    @Override
    public JsonTypeInfo.Id getMechanism() {
        return JsonTypeInfo.Id.CUSTOM;
    }
}
