package com.mkrt4an.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by 123 on 24.11.2016.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Record not found")
public class WebInterfaceException extends RuntimeException {

    public WebInterfaceException() {
        super();
    }

    public WebInterfaceException(String msg) {
        super(msg);
    }
}
