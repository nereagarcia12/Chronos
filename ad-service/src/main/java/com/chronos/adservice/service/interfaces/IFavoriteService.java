package com.chronos.adservice.service.interfaces;

import com.chronos.adservice.dto.FavoriteRequestDto;
import com.chronos.adservice.dto.FavoriteResponseDto;
import com.chronos.adservice.model.Favorite;

import java.util.List;

public interface IFavoriteService {

    public List<FavoriteResponseDto> getAllFavorites(Integer userId);
    public void saveFavorite(FavoriteRequestDto favoriteRequestDto);
    public void deleteFavorite(FavoriteRequestDto favoriteRequestDto);
    public void deleteFavoritesByUser(Integer userId);

}
