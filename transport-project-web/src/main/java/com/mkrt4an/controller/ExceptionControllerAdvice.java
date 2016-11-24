package com.mkrt4an.controller;

import com.mkrt4an.exception.TransportProjectException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionControllerAdvice {
    
    private String errorViewPath = "/Error";
    
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(TransportProjectException.class)
    public ModelAndView exception(Exception e) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        
        ModelAndView mav = new ModelAndView();
        mav.setViewName(errorViewPath);
        mav.addObject("errorCode", status.value());
        mav.addObject("errorDiscription", status.getReasonPhrase());
        mav.addObject("errorMsg", e.getMessage());
        return mav;        
    }
}
