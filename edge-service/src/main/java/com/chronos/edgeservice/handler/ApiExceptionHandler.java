package com.chronos.edgeservice.handler;

import feign.FeignException;
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

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<Object> noPresentUser(FeignException feignException, HttpServletResponse response) throws IOException {
        return new ResponseEntity<Object>(feignException.contentUTF8(), new HttpHeaders(), feignException.status());
    }

}
