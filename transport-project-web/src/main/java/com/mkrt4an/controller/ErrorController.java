package com.mkrt4an.controller;

//import org.apache.commons.httpclient.HttpStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ErrorController {
    

    @RequestMapping(value = "/error")
    public String errorHandeler(Model model, HttpServletResponse response,
                                HttpServletRequest request) {
//        model.addAttribute("errorCode", response.getStatus());
//        model.addAttribute("errorDiscription", HttpStatus.getStatusText(response.getStatus()));
        
        String msg = (String) request.getAttribute("javax.servlet.error.message");
        model.addAttribute("errorMsg", msg);
        
        return "errorPage";
    }
}
