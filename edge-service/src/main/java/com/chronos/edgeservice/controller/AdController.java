package com.chronos.edgeservice.controller;

import com.chronos.edgeservice.apiresponse.ad.*;
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
    public List<AdResponseDto> filter(@RequestParam (name = "word",required = false) String word, @RequestParam (name = "categoryId",required = false) Integer categoryId){
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

    @GetMapping("/favorite/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<FavoriteResponseDto> getAllFavoriteByUser(@PathVariable(name ="userId" ) Integer userId){
        return adClient.getAllFavoriteByUser(userId);
    }

    @PostMapping("/favorite")
    @ResponseStatus(HttpStatus.CREATED)
    public void createFavorite(@RequestBody FavoriteRequestDto favoriteRequestDto){
        adClient.createFavorite(favoriteRequestDto);
    }

    @DeleteMapping("/favorites/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFavorites(@PathVariable(name = "id") FavoriteRequestDto favoriteRequestDto){
       adClient.deleteFavorites(favoriteRequestDto);
    }

    @DeleteMapping("/favorites/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFavorites(@PathVariable(name ="userId" ) Integer userId){
       adClient.deleteFavorites(userId);
    }

}
