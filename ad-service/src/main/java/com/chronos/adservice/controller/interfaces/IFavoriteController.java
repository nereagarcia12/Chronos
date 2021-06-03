package com.chronos.adservice.controller.interfaces;

import com.chronos.adservice.dto.FavoriteRequestDto;
import com.chronos.adservice.model.Favorite;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IFavoriteController {


    @GetMapping("/favorite/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Favorite> getAllFavoriteByUser( Integer userId);

    @PostMapping("/favorite")
    @ResponseStatus(HttpStatus.CREATED)
    public void createFavorite( FavoriteRequestDto favoriteRequestDto);

    @DeleteMapping("/favorites")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFavorites(FavoriteRequestDto favoriteRequestDto);

    @DeleteMapping("/favorites/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFavorites(Integer userId);


}
