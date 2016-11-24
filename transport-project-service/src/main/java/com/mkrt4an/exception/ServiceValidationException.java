package com.mkrt4an.exception;

/**
 * Created by 123 on 24.11.2016.
 */
public class ServiceValidationException extends TransportProjectException{

    public ServiceValidationException() {
    }

    public ServiceValidationException(String message) {
        super(message);
    }

    public ServiceValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceValidationException(Throwable cause) {
        super(cause);
    }
}

