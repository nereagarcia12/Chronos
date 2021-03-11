package com.chronos.adservice.controller.interfaces;

import com.chronos.adservice.dto.AdRequestDto;
import com.chronos.adservice.dto.AdResponseDto;
import com.chronos.adservice.dto.FilterSearchDto;
import com.chronos.adservice.enums.Status;
import com.chronos.adservice.model.Ad;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface IAdController {
    public List<AdResponseDto> getAllAds ();
    public List<AdResponseDto> getAdsByUserId (Integer userId);
    public List<AdResponseDto> getAdsByStatus (String status);
    public AdResponseDto getAdById (Integer id);
    public AdResponseDto createAd (AdRequestDto adRequestDto);
    public List<AdResponseDto> filter (String word,Integer categoryId);
    public void editAd(Integer id,AdRequestDto adRequestDto);
    public void deleteAd(Integer id);
    public void deleteAdByUser(Integer userId);
}
