package com.chronos.edgeservice.controller;

import com.chronos.edgeservice.apiresponse.transaction.CompleteTransactionResponseDto;
import com.chronos.edgeservice.apiresponse.transaction.TransactionRequestDto;
import com.chronos.edgeservice.apiresponse.transaction.TransactionResponseDto;
import com.chronos.edgeservice.client.AdClient;
import com.chronos.edgeservice.client.TransactionClient;
import com.chronos.edgeservice.client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TransactionController {

    @Autowired
    private TransactionClient transactionClient;
    @Autowired
    private UserClient userClient;
    @Autowired
    private AdClient adclient;

    @PostMapping("/transaction")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTransaction (@RequestBody @Valid TransactionRequestDto transactionRequestDto){
        transactionClient.createTransaction(transactionRequestDto);
    }

    @PutMapping("/transaction/{id}/user/{userId}/accept")
    @ResponseStatus(HttpStatus.OK)
    public void acceptTransaction (@PathVariable(name = "id") Integer id, @PathVariable (name = "userId") Integer userId){
        transactionClient.acceptTransaction(id, userId);
    }

    @PutMapping("/transaction/{id}/user/{userId}/refuse")
    @ResponseStatus(HttpStatus.OK)
    public void refuseTransaction (@PathVariable (name = "id") Integer id, @PathVariable (name = "userId") Integer userId){
        transactionClient.refuseTransaction(id, userId);
    }
    @PutMapping("/transaction/{id}/user/{userId}/complete")
    @ResponseStatus(HttpStatus.OK)
    public void completeTransaction (@PathVariable (name = "id") Integer id, @PathVariable (name = "userId") Integer userId){
        transactionClient.completeTransaction(id, userId);
    }

    @GetMapping("/transactions/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<CompleteTransactionResponseDto> getAllTransactionByUserId(@PathVariable (name = "id") Integer userId){
        return transactionClient.getAllTransactionByUserId(userId).stream().map(transactionResponseDto ->
                new CompleteTransactionResponseDto(
                        transactionResponseDto.getId(),
                        userClient.findByUserById(transactionResponseDto.getOriginUserId()),
                        userClient.findByUserById(transactionResponseDto.getReceiverUserId()),
                        transactionResponseDto.getAmount(),
                        transactionResponseDto.getDescription(),
                        adclient.getAdById(transactionResponseDto.getAdId()),
                        transactionResponseDto.getStatus())).collect(Collectors.toList());
    }

}
