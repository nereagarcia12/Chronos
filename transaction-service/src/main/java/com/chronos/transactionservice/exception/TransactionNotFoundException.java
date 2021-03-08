package com.chronos.transactionservice.exception;

public class TransactionNotFoundException extends RuntimeException{
    public TransactionNotFoundException() {
        super("Transaction not Found");
    }
}
