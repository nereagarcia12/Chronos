package com.chronos.transactionservice.exception;

public class TransactionNotOwnedByUserException extends RuntimeException {
    public TransactionNotOwnedByUserException() {
        super("Transaction not owned by user");
    }
}
