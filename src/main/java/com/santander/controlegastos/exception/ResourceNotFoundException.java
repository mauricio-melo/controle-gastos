package com.santander.controlegastos.exception;

public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 887057760479428846L;

    public ResourceNotFoundException(final String message) {
        super(message);
    }
}