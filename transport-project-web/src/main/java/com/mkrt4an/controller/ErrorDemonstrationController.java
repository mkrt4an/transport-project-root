package com.mkrt4an.controller;

import com.mkrt4an.exception.TransportProjectException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorDemonstrationController {

    @RequestMapping("/transportProjectException")
    public void throwLogiwebServiceException() throws TransportProjectException {
        throw new TransportProjectException("Test TransportProjectException exception.");
    }
}
