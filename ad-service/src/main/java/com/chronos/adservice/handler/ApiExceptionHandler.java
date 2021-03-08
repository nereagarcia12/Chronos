package com.chronos.adservice.handler;

import com.chronos.adservice.exceptions.NoAdPresent;
import com.chronos.adservice.exceptions.NoPresentCategory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(NoPresentCategory.class)
    public void noPresentUser(NoPresentCategory noPresentCategory, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, noPresentCategory.getMessage());}

    @ExceptionHandler(NoAdPresent.class)
    public void NoAdPresent(NoAdPresent noAdPresent, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, noAdPresent.getMessage());}
}
