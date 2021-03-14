package com.chronos.userservice.handler;

import com.chronos.userservice.exceptions.EmailAlreadyRegisteredException;
import com.chronos.userservice.exceptions.InsufficientHoursException;
import com.chronos.userservice.exceptions.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> userNotFound(UserNotFoundException userNotFoundException, HttpServletResponse response) throws IOException {
        return new ResponseEntity<Object>(Map.of("error", userNotFoundException.getLocalizedMessage())
                , new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InsufficientHoursException.class)
    public ResponseEntity<Object> insufficientHoursException(InsufficientHoursException insufficientHoursException, HttpServletResponse response) throws IOException {
        return new ResponseEntity<Object>(Map.of("error", insufficientHoursException.getLocalizedMessage())
                , new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailAlreadyRegisteredException.class)
    public ResponseEntity<Object> emailAlreadyRegistered(EmailAlreadyRegisteredException emailAlreadyRegisteredException, HttpServletResponse response) throws IOException {
        return new ResponseEntity<Object>(Map.of("error", emailAlreadyRegisteredException.getLocalizedMessage())
                , new HttpHeaders(), HttpStatus.PRECONDITION_FAILED);
    }
}
