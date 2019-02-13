package com.grupo5.definiciones;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController  {
    
    @RequestMapping("/error")
    public String handleError(Model model, HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
        
        model.addAttribute("statusCode", statusCode);
        String errorMessage;
        if (exception==null)
        	errorMessage = "Error desconocido";
        else
        	errorMessage = exception.getMessage();
        model.addAttribute("errorMessage", errorMessage);
        return "error";
    }
 
    @Override
    public String getErrorPath() {
        return "/error";
    }
}
