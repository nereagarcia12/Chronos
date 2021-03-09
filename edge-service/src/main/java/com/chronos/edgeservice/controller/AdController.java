package com.chronos.edgeservice.controller;

import com.chronos.edgeservice.apiresponse.ad.AdRequestDto;
import com.chronos.edgeservice.apiresponse.ad.AdResponseDto;
import com.chronos.edgeservice.apiresponse.ad.CategoryResponseDto;
import com.chronos.edgeservice.client.AdClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("/ads/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<AdResponseDto> getAdsByUserId (@PathVariable(name = "id") Integer userId){
        return adClient.getAdsByUserId(userId);
    }

    @GetMapping("/ads/{status}")
    @ResponseStatus(HttpStatus.OK)
    public List<AdResponseDto> getAdsByStatus (@PathVariable (name = "status") String status){
        return adClient.getAdsByStatus(status);
    }
    @GetMapping("/ad/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AdResponseDto getAdById (@PathVariable (name = "id") Integer id){
        return adClient.getAdById(id);
    }

    @PostMapping("/ad")
    @ResponseStatus(HttpStatus.CREATED)
    public AdResponseDto createAd (@RequestBody @Valid AdRequestDto adRequestDto){
        return adClient.createAd(adRequestDto);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<AdResponseDto> filter (@RequestParam (name = "word",required = false) String word, @RequestParam (name = "categoryId",required = false) Integer categoryId){
        return adClient.filter(word,categoryId);
    }


    @PutMapping("/ad/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void editAd(@PathVariable (name = "id") Integer id, @RequestBody @Valid AdRequestDto adRequestDto){
        adClient.editAd(id,adRequestDto);
    }

    @DeleteMapping("/ad/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAd(@PathVariable (name = "id") Integer id){
        adClient.deleteAd(id);
    }

    @GetMapping("/categories")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryResponseDto> getAllCategories(){
        return adClient.getAllCategories();
    }

}
