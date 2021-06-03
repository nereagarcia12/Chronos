package com.chronos.adservice.controller.impl;

import com.chronos.adservice.dto.AdResponseDto;
import com.chronos.adservice.dto.FavoriteRequestDto;
import com.chronos.adservice.dto.FavoriteResponseDto;
import com.chronos.adservice.model.Favorite;
import com.chronos.adservice.service.impl.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;


    @GetMapping("/favorite/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<FavoriteResponseDto> getAllFavoriteByUser(@PathVariable(name = "userId") Integer userId) {
        return favoriteService.getAllFavorites(userId);
    }

    @PostMapping("/favorite")
    @ResponseStatus(HttpStatus.CREATED)
    public void createFavorite(@RequestBody FavoriteRequestDto favoriteRequestDto) {
        favoriteService.saveFavorite(favoriteRequestDto);
    }

    @DeleteMapping("/favorites/{adId}/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFavorites(@PathVariable Integer adId, @PathVariable Integer userId) {
        favoriteService.deleteFavorite(new FavoriteRequestDto(adId, userId));
    }

    @DeleteMapping("/favorites/{userId}/user")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFavorites(@PathVariable(name = "userId") Integer userId) {
        favoriteService.deleteFavoritesByUser(userId);
    }


}
