package com.chronos.transactionservice.controller.impl;

import com.chronos.transactionservice.controller.interfaces.ITransactionController;
import com.chronos.transactionservice.dto.TransactionRequestDto;
import com.chronos.transactionservice.dto.TransactionResponseDto;
import com.chronos.transactionservice.service.impl.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TransactionController implements ITransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transaction")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTransaction (@RequestBody @Valid TransactionRequestDto transactionRequestDto){
        transactionService.makeTransaction(transactionRequestDto);
    }

    @PutMapping("/transaction/{id}/user/{userId}/accept")
    @ResponseStatus(HttpStatus.OK)
    public void acceptTransaction (@PathVariable (name = "id") Integer id, @PathVariable (name = "userId") Integer userId){
        transactionService.acceptTransaction(id,userId);
    }

    @PutMapping("/transaction/{id}/user/{userId}/refuse")
    @ResponseStatus(HttpStatus.OK)
    public void refuseTransaction (@PathVariable (name = "id") Integer id, @PathVariable (name = "userId") Integer userId){
        transactionService.refuseTransaction(id,userId);
    }
    @PutMapping("/transaction/{id}/user/{userId}/complete")
    @ResponseStatus(HttpStatus.OK)
    public void completeTransaction (@PathVariable (name = "id") Integer id, @PathVariable (name = "userId") Integer userId){
        transactionService.completeTransaction(id,userId);
    }

    @GetMapping("/transactions/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionResponseDto> getAllTransactionByUserId(@PathVariable (name = "id") Integer userId){
        return transactionService.getTransactionByUser(userId);
    }

    @DeleteMapping("/transactions/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTransactionByUser(@PathVariable (name = "userId") Integer userId){
         transactionService.deleteTransactionByUser(userId);
    }

}
