package com.chronos.transactionservice.client;

import com.chronos.transactionservice.apiresponse.AdResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient("ad-service")
public interface AdClient {

    @GetMapping("/ad/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AdResponseDto getAdById (@PathVariable(name = "id") Integer id);
}
