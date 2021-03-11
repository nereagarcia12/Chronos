package com.chronos.transactionservice.service.interfaces;

import com.chronos.transactionservice.dto.TransactionRequestDto;
import com.chronos.transactionservice.dto.TransactionResponseDto;


import java.util.List;


public interface ITransactionService {

    public void makeTransaction(TransactionRequestDto transactionRequestDto);

    public void acceptTransaction(Integer id, Integer userId);

    public void refuseTransaction(Integer id, Integer userId);
    public void completeTransaction(Integer id, Integer userId);

    public List<TransactionResponseDto> getTransactionByUser(Integer userId);

    public void deleteTransactionByUser(Integer userId);
}
