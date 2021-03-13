package com.chronos.adservice.handler;

import com.chronos.adservice.exceptions.AdNotFoundException;
import com.chronos.adservice.exceptions.CategoryNotFoundException;
import feign.FeignException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Object> noPresentCategory(CategoryNotFoundException categoryNotFoundException, HttpServletResponse response) {
        return new ResponseEntity<>(Map.of("error", categoryNotFoundException.getLocalizedMessage())
                , new HttpHeaders(), HttpStatus.NOT_FOUND);}

    @ExceptionHandler(AdNotFoundException.class)
    public ResponseEntity<Object> noAdPresent(AdNotFoundException adNotFoundException, HttpServletResponse response) {
        return new ResponseEntity<>(Map.of("error", adNotFoundException.getLocalizedMessage())
                , new HttpHeaders(), HttpStatus.NOT_FOUND);}

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<Object> feignException(FeignException feignException, HttpServletResponse response) {
        return new ResponseEntity<>(feignException.contentUTF8(), new HttpHeaders(), feignException.status());
    }
}
