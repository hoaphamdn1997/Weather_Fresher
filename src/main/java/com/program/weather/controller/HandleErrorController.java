package com.program.weather.controller;

import org.springframework.boot.web.servlet.error.ErrorController;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * The type Handle error controller.
 */
@Controller
public class HandleErrorController implements ErrorController {
    /**
     * Handle error string.
     *
     * @param request the request
     * @return the string
     */
    @RequestMapping("/error")
    /**
     * handle error
     */
    public String HandleError(HttpServletRequest request) {

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error/404";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error/500";
            }
        }
        return "error/404";

    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
