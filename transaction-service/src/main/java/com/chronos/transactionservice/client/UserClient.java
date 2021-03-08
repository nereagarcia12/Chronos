package com.chronos.transactionservice.client;

import com.chronos.transactionservice.apiresponse.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@FeignClient("user-service")
public interface UserClient {
    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDto findByUserById(@PathVariable(name = "id") Integer id);

    @PutMapping("/user/{id}/increase/balance")
    @ResponseStatus(HttpStatus.OK)
    public void increaseBalanceHours(@PathVariable (name = "id") Integer id, @RequestBody @Valid @Min(value = 0,message = "i can't less than 0") Integer amount);

    @PutMapping("/user/{id}/decrease/balance")
    @ResponseStatus(HttpStatus.OK)
    public void decreaseBalanceHours(@PathVariable (name = "id") Integer id, @RequestBody @Valid @Min(value = 0,message = "i can't less than 0") Integer amount);

}
