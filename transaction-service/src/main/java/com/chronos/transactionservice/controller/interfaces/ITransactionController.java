package com.chronos.transactionservice.controller.interfaces;

import com.chronos.transactionservice.dto.TransactionRequestDto;
import com.chronos.transactionservice.dto.TransactionResponseDto;


import java.util.List;

public interface ITransactionController {


    public void createTransaction(TransactionRequestDto transactionRequestDto);

    public void acceptTransaction(Integer id, Integer userId);

    public void refuseTransaction(Integer id, Integer userId);

    public void completeTransaction(Integer id, Integer userId);

    public List<TransactionResponseDto> getAllTransactionByUserId(Integer userId);
}
