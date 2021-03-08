package com.chronos.transactionservice.exception;

public class UnexpectedTransactionStatusException extends RuntimeException {
    public UnexpectedTransactionStatusException() {
        super("Unexpected Transaction Status");
    }
}
