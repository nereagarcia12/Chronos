package com.chronos.userservice.handler;

import com.chronos.userservice.exceptions.InsufficientHoursException;
import com.chronos.userservice.exceptions.NoPresentUser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ApiExceptionHandler {


    @ExceptionHandler(NoPresentUser.class)
    public ResponseEntity<Object> noPresentUser(NoPresentUser noPresentUser, HttpServletResponse response) throws IOException {
        return new ResponseEntity<Object>(Map.of("error",  noPresentUser.getLocalizedMessage())
               , new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InsufficientHoursException.class)
    public ResponseEntity<Object> insufficientHoursException(InsufficientHoursException insufficientHoursException, HttpServletResponse response) throws IOException {
        return new ResponseEntity<Object>(Map.of("error",  insufficientHoursException.getLocalizedMessage())
                , new HttpHeaders(), HttpStatus.BAD_REQUEST);}

}
