package com.chronos.adservice.service.impl;

import com.chronos.adservice.apiresponse.UserResponseDto;
import com.chronos.adservice.client.UserClient;
import com.chronos.adservice.dto.AdRequestDto;
import com.chronos.adservice.dto.AdResponseDto;
import com.chronos.adservice.dto.FilterSearchRequestDto;
import com.chronos.adservice.enums.Status;
import com.chronos.adservice.exceptions.AdNotFoundException;
import com.chronos.adservice.exceptions.CategoryNotFoundException;
import com.chronos.adservice.model.Ad;
import com.chronos.adservice.model.Category;
import com.chronos.adservice.repository.AdRepository;
import com.chronos.adservice.repository.CategoryRepository;
import com.chronos.adservice.repository.FavoriteRepository;
import com.chronos.adservice.service.interfaces.IAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdService implements IAdService {

    @Autowired
    private AdRepository adRepository;
    @Autowired
    private UserClient userClient;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private FavoriteRepository favoriteRepository;

    public List<AdResponseDto> findAll() {
        return adRepository.findAll().stream().map(Ad::toResponseDto).collect(Collectors.toList());
    }

    public List<AdResponseDto> findByStatus(Status status) {
        return adRepository.findByStatus(status).stream().map(Ad::toResponseDto).collect(Collectors.toList());
    }
    public List<AdResponseDto> adsByUser(Integer id){
        return adRepository.findByUserId(id).stream().map(Ad::toResponseDto).collect(Collectors.toList());
    }

    public AdResponseDto adById(Integer id){
        return getAd(id).toResponseDto();
    }

    public AdResponseDto createAd(AdRequestDto adRequestDto) {
        UserResponseDto user = userClient.findByUserById(adRequestDto.getUserId());
        Category category = getCategory(adRequestDto);
        Ad ad = new Ad(adRequestDto.getTitle(), adRequestDto.getDescription(), adRequestDto.getAvailability(), user.getId(), category);
        return adRepository.save(ad).toResponseDto();
    }

    public List<AdResponseDto> search(FilterSearchRequestDto filterSearchRequestDto){
        if (filterSearchRequestDto.getWord() != null && filterSearchRequestDto.getCategoryId() == null){
           return adRepository.findByTitleContainingIgnoreCase(filterSearchRequestDto.getWord()).stream().map(Ad::toResponseDto).collect(Collectors.toList());
        } else if (filterSearchRequestDto.getWord() == null && filterSearchRequestDto.getCategoryId() != null){
            return adRepository.findByCategory_Id(filterSearchRequestDto.getCategoryId()).stream().map(Ad::toResponseDto).collect(Collectors.toList());
        } else{
            return adRepository.findAll().stream().map(Ad::toResponseDto).collect(Collectors.toList());
        }
    }

    public void editAd(AdRequestDto adRequestDto, Integer id){
        Ad ad = getAd(id);
        Category category = getCategory(adRequestDto);
        ad.setTitle(adRequestDto.getTitle());
        ad.setDescription(adRequestDto.getDescription());
        ad.setAvailability(adRequestDto.getAvailability());
        ad.setCategory(category);
        adRepository.save(ad);
    }

    public void deleteAd(Integer id){
        Ad ad = getAd(id);
        favoriteRepository.deleteByAdId(id);
        adRepository.delete(ad);
    }

    public void deleteAdsByUser(Integer userId){
        adRepository.findByUserId(userId).forEach(ad -> {
            favoriteRepository.deleteByAdId(ad.getUserId());
            adRepository.delete(ad);
        });
    }


    private Ad getAd(Integer id) {
        return adRepository.findById(id).orElseThrow(AdNotFoundException::new);
    }

    private Category getCategory(AdRequestDto adRequestDto) {
        return categoryRepository.findById(adRequestDto.getCategoryId()).orElseThrow(CategoryNotFoundException::new);
    }
}
