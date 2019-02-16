package com.santander.controlegastos.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 887057760479428896L;

    public ResourceNotFoundException (final String message) {
        super(message);
    }
}
