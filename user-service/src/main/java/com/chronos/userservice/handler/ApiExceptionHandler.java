package com.chronos.userservice.handler;

import com.chronos.userservice.exceptions.InsufficientHoursException;
import com.chronos.userservice.exceptions.NoPresentUser;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(NoPresentUser.class)
    public void noPresentUser(NoPresentUser noPresentUser, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, noPresentUser.getMessage());}

    @ExceptionHandler(InsufficientHoursException.class)
    public void noPresentUser(InsufficientHoursException insufficientHoursException, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, insufficientHoursException.getMessage());}

}
