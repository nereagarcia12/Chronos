package com.chronos.edgeservice.client;


import com.chronos.edgeservice.apiresponse.transaction.TransactionRequestDto;
import com.chronos.edgeservice.apiresponse.transaction.TransactionResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient("transaction-service")
public interface TransactionClient {

    @PostMapping("/transaction")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTransaction (@RequestBody @Valid TransactionRequestDto transactionRequestDto);

    @PutMapping("/transaction/{id}/user/{userId}/accept")
    @ResponseStatus(HttpStatus.OK)
    public void acceptTransaction (@PathVariable(name = "id") Integer id, @PathVariable (name = "userId") Integer userId);

    @PutMapping("/transaction/{id}/user/{userId}/refuse")
    @ResponseStatus(HttpStatus.OK)
    public void refuseTransaction (@PathVariable (name = "id") Integer id, @PathVariable (name = "userId") Integer userId);
    @PutMapping("/transaction/{id}/user/{userId}/complete")
    @ResponseStatus(HttpStatus.OK)
    public void completeTransaction (@PathVariable (name = "id") Integer id, @PathVariable (name = "userId") Integer userId);

    @GetMapping("/transactions/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionResponseDto> getAllTransactionByUserId(@PathVariable (name = "id") Integer userId);

    @DeleteMapping("/transactions/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTransactionByUser(@PathVariable (name = "userId") Integer userId);

}
