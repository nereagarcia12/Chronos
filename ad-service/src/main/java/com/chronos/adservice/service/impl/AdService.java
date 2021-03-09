package com.chronos.adservice.service.impl;

import com.chronos.adservice.apiresponse.UserResponseDto;
import com.chronos.adservice.client.UserClient;
import com.chronos.adservice.dto.AdRequestDto;
import com.chronos.adservice.dto.AdResponseDto;
import com.chronos.adservice.dto.FilterSearchDto;
import com.chronos.adservice.enums.Status;
import com.chronos.adservice.exceptions.NoAdPresent;
import com.chronos.adservice.exceptions.NoPresentCategory;
import com.chronos.adservice.model.Ad;
import com.chronos.adservice.model.Category;
import com.chronos.adservice.repository.AdRepository;
import com.chronos.adservice.repository.CategoryRepository;
import com.chronos.adservice.service.interfaces.IAdService;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        return adRepository.findById(id).orElseThrow(NoAdPresent::new).toResponseDto();
    }

    public AdResponseDto createAd(AdRequestDto adRequestDto) {
        try {
            UserResponseDto user = userClient.findByUserById(adRequestDto.getUserId());
        } catch (FeignException e) {
            throw new ResponseStatusException(e.status(), e.getCause().getMessage(), e.getCause());
        }
        Category category = categoryRepository.findById(adRequestDto.getCategoryId()).orElseThrow(NoPresentCategory::new);
        Ad ad = new Ad(adRequestDto.getTitle(), adRequestDto.getDescription(), adRequestDto.getAvailability(), adRequestDto.getUserId(), category);
        return adRepository.save(ad).toResponseDto();
    }

    public List<AdResponseDto> search(FilterSearchDto filterSearchDto){
        if (filterSearchDto.getWord() != null && filterSearchDto.getCategoryId() == null){
           return adRepository.findByTitleContains(filterSearchDto.getWord()).stream().map(Ad::toResponseDto).collect(Collectors.toList());
        } else if (filterSearchDto.getWord() == null && filterSearchDto.getCategoryId() != null){
            return adRepository.findByCategory_Id(filterSearchDto.getCategoryId()).stream().map(Ad::toResponseDto).collect(Collectors.toList());
        } else if (filterSearchDto.getWord() != null && filterSearchDto.getCategoryId() != null){
            return adRepository.findByTitleContainsAndCategory_Id(filterSearchDto.getWord(), filterSearchDto.getCategoryId()).stream().map(Ad::toResponseDto).collect(Collectors.toList());
        } else{
            return adRepository.findAll().stream().map(Ad::toResponseDto).collect(Collectors.toList());
        }
    }

    public void editAd(AdRequestDto adRequestDto, Integer id){
        Ad ad = adRepository.findById(id).orElseThrow(NoAdPresent::new);
        ad.setTitle(adRequestDto.getTitle());
        ad.setDescription(adRequestDto.getDescription());
        ad.setAvailability(adRequestDto.getAvailability());
        ad.setCategory(ad.getCategory());
        adRepository.save(ad);
    }


    public void deleteAd(Integer id){

        Ad ad = adRepository.findById(id).orElseThrow(NoAdPresent::new);
        adRepository.delete(ad);
    }


}
