package com.ritik.accountservice.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
        super("%s not found with the given %s: %s".formatted(resourceName, fieldName, fieldValue));
    }
}
