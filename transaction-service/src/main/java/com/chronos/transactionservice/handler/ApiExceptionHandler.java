package com.chronos.transactionservice.handler;

import com.chronos.transactionservice.exception.TransactionNotFoundException;
import com.chronos.transactionservice.exception.TransactionNotOwnedByUserException;
import com.chronos.transactionservice.exception.UnexpectedTransactionStatusException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(TransactionNotFoundException.class)
    public void noPresentUser(TransactionNotFoundException transactionNotFoundException, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, transactionNotFoundException.getMessage());}

    @ExceptionHandler(TransactionNotOwnedByUserException.class)
    public void noPresentUser(TransactionNotOwnedByUserException transactionNotOwnedByUserException, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_FORBIDDEN, transactionNotOwnedByUserException.getMessage());}

    @ExceptionHandler(UnexpectedTransactionStatusException.class)
    public void noPresentUser(UnexpectedTransactionStatusException unexpectedTransactionStatusException, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, unexpectedTransactionStatusException.getMessage());}

}
