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


    public List<Ad> getAllAds ();

    public List<Ad> getAdsByUserId (@PathVariable(name = "id") Integer userId);


    public List<Ad> getAdsByStatus (@PathVariable (name = "status") String status);

    public AdResponseDto getAdById (@PathVariable (name = "id") Integer id);


    public AdResponseDto createAd (@RequestBody @Valid AdRequestDto adRequestDto);


    public List<AdResponseDto> filter (@RequestParam (name = "word") String word, @RequestParam (name = "categoryId") Integer categoryId);


    public void editAd(@PathVariable (name = "id") Integer id, @RequestBody @Valid AdRequestDto adRequestDto);

    public void deleteAd(@PathVariable (name = "id") Integer id);
}
