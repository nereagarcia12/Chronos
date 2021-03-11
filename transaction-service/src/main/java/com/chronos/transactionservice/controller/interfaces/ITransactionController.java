package com.chronos.transactionservice.controller.interfaces;

import com.chronos.transactionservice.dto.TransactionRequestDto;
import com.chronos.transactionservice.dto.TransactionResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;


import java.util.List;

public interface ITransactionController {


    public void createTransaction(TransactionRequestDto transactionRequestDto);

    public void acceptTransaction(Integer id, Integer userId);

    public void refuseTransaction(Integer id, Integer userId);

    public void completeTransaction(Integer id, Integer userId);

    public List<TransactionResponseDto> getAllTransactionByUserId(Integer userId);

    public void deleteTransactionByUser(Integer userId);
}
