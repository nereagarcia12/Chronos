package com.chronos.edgeservice.controller;

import com.chronos.edgeservice.apiresponse.ad.AdResponseDto;
import com.chronos.edgeservice.client.AdClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdController {

    @Autowired
    AdClient adClient;

    @GetMapping("/ads")
    @ResponseStatus(HttpStatus.OK)
    public List<AdResponseDto> getAllAds (){
        return adClient.getAllAds();
    }

}
