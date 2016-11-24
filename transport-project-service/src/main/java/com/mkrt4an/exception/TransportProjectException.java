package com.mkrt4an.exception;

/**
 * Created by 123 on 24.11.2016.
 */
public class TransportProjectException extends Exception{

    public TransportProjectException() {
        super();
    }

    public TransportProjectException(String message) {
        super(message);
    }


    public TransportProjectException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransportProjectException(Throwable cause) {
        super(cause);
    }
}


