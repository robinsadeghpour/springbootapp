package com.demo.springbootapp.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorControllerImpl implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Integer httpErrorCode = getHttpErrorCode(request);
        if (httpErrorCode == null) {
            return "error";
        }

        switch (httpErrorCode) {
            case 400: {
                return "error-400";
            }
            case 404: {
                return "error-404";
            }
            case 500: {
                return "error-500";
            }
        }
        return "error";
    }

    private Integer getHttpErrorCode(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status == null) {
            return null;
        }

        return Integer.parseInt(status.toString());
    }
}
