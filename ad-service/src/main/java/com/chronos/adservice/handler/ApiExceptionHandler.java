package com.chronos.adservice.handler;

import com.chronos.adservice.exceptions.NoAdPresent;
import com.chronos.adservice.exceptions.NoPresentCategory;
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

    @ExceptionHandler(NoPresentCategory.class)
    public ResponseEntity<Object> noPresentCategory(NoPresentCategory noPresentCategory, HttpServletResponse response) throws IOException {
        return new ResponseEntity<Object>(Map.of("error",  noPresentCategory.getLocalizedMessage())
                , new HttpHeaders(), HttpStatus.NOT_FOUND);}

    @ExceptionHandler(NoAdPresent.class)
    public ResponseEntity<Object> NoAdPresent(NoAdPresent noAdPresent, HttpServletResponse response) throws IOException {
        return new ResponseEntity<Object>(Map.of("error",  noAdPresent.getLocalizedMessage())
                , new HttpHeaders(), HttpStatus.NOT_FOUND);}
}
