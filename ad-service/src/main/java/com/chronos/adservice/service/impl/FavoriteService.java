package com.chronos.adservice.service.impl;

import com.chronos.adservice.apiresponse.UserResponseDto;
import com.chronos.adservice.client.UserClient;
import com.chronos.adservice.dto.AdResponseDto;
import com.chronos.adservice.dto.FavoriteRequestDto;
import com.chronos.adservice.dto.FavoriteResponseDto;
import com.chronos.adservice.exceptions.AdNotFoundException;
import com.chronos.adservice.model.Ad;
import com.chronos.adservice.model.Favorite;
import com.chronos.adservice.repository.AdRepository;
import com.chronos.adservice.repository.FavoriteRepository;
import com.chronos.adservice.service.interfaces.IFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FavoriteService implements IFavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;
    @Autowired
    private AdRepository adRepository;
    @Autowired
    private UserClient userClient;


    public List<FavoriteResponseDto> getAllFavorites(Integer userId){
        List<FavoriteResponseDto> favorites = favoriteRepository.findByUserId(userId).stream().map(favorite -> new FavoriteResponseDto(favorite.getAdId(), favorite.getUserId())).collect(Collectors.toList());
        return favorites;
    }


    public void saveFavorite(FavoriteRequestDto favoriteRequestDto){
        UserResponseDto user = userClient.findByUserById(favoriteRequestDto.getUserId());
        Ad ad = adRepository.findById(favoriteRequestDto.getAdId()).orElseThrow(AdNotFoundException::new);

        Favorite favorite = favoriteRepository.save(new Favorite(favoriteRequestDto.getAdId(),favoriteRequestDto.getUserId()));
    }

    public void deleteFavorite(FavoriteRequestDto favoriteRequestDto){
        Optional<Favorite> favorite = favoriteRepository.findByAdIdAndUserId(favoriteRequestDto.getAdId(),favoriteRequestDto.getUserId());

        favorite.ifPresent(value -> favoriteRepository.delete(value));

    }

    public void deleteFavoritesByUser(Integer userId){
        List<Favorite> favorites = favoriteRepository.findByUserId(userId);

        favoriteRepository.deleteAll(favorites);
    }

}
