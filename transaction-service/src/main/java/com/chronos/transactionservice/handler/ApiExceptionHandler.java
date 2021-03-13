package com.chronos.transactionservice.handler;

import com.chronos.transactionservice.exception.TransactionNotFoundException;
import com.chronos.transactionservice.exception.TransactionNotOwnedByUserException;
import com.chronos.transactionservice.exception.UnexpectedTransactionStatusException;
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
    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<Object> transactionNotFoundException(TransactionNotFoundException transactionNotFoundException, HttpServletResponse response) throws IOException {
        return new ResponseEntity<>(Map.of("error", transactionNotFoundException.getLocalizedMessage())
                , new HttpHeaders(), HttpStatus.NOT_FOUND);}

    @ExceptionHandler(TransactionNotOwnedByUserException.class)
    public ResponseEntity<Object> transactionNotOwnedByUserException(TransactionNotOwnedByUserException transactionNotOwnedByUserException, HttpServletResponse response) throws IOException {
        return new ResponseEntity<>(Map.of("error", transactionNotOwnedByUserException.getLocalizedMessage())
                , new HttpHeaders(), HttpStatus.FORBIDDEN);}

    @ExceptionHandler(UnexpectedTransactionStatusException.class)
    public ResponseEntity<Object> unexpectedTransactionStatusException(UnexpectedTransactionStatusException unexpectedTransactionStatusException, HttpServletResponse response) throws IOException {
        return new ResponseEntity<>(Map.of("error", unexpectedTransactionStatusException.getLocalizedMessage())
                , new HttpHeaders(), HttpStatus.PRECONDITION_FAILED);}

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<Object> feignException(FeignException feignException, HttpServletResponse response) throws IOException {
        return new ResponseEntity<>(feignException.contentUTF8(), new HttpHeaders(), feignException.status());
    }

}
