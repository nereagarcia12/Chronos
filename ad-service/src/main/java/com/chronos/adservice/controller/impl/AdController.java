package com.chronos.adservice.controller.impl;

import com.chronos.adservice.controller.interfaces.IAdController;
import com.chronos.adservice.dto.AdRequestDto;
import com.chronos.adservice.dto.AdResponseDto;
import com.chronos.adservice.dto.FilterSearchRequestDto;
import com.chronos.adservice.enums.Status;
import com.chronos.adservice.service.impl.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
public class AdController implements IAdController {

    @Autowired
    private AdService adService;

    @GetMapping("/ads")
    @ResponseStatus(HttpStatus.OK)
    public List<AdResponseDto> getAllAds (){
        return adService.findAll();
    }

    @GetMapping("/ads/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<AdResponseDto> getAdsByUserId (@PathVariable (name = "id") Integer userId){
        return adService.adsByUser(userId);
    }

    @GetMapping("/ads/{status}")
    @ResponseStatus(HttpStatus.OK)
    public List<AdResponseDto> getAdsByStatus (@PathVariable (name = "status") String status){
        return adService.findByStatus(Status.valueOf(status.toUpperCase()));
    }

    @GetMapping("/ad/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AdResponseDto getAdById (@PathVariable (name = "id") Integer id){
        return adService.adById(id);
    }

    @PostMapping("/ad")
    @ResponseStatus(HttpStatus.CREATED)
    public AdResponseDto createAd (@RequestBody @Valid AdRequestDto adRequestDto){
        return adService.createAd(adRequestDto);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<AdResponseDto> filter (@RequestParam (name = "word",required = false) String word, @RequestParam (name = "categoryId",required = false) Integer categoryId){
        return adService.search(new FilterSearchRequestDto(word, categoryId));
    }

    @PutMapping("/ad/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void editAd(@PathVariable (name = "id") Integer id, @RequestBody @Valid AdRequestDto adRequestDto){
        adService.editAd(adRequestDto, id);
    }

    @DeleteMapping("/ad/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAd(@PathVariable (name = "id") Integer id){
        adService.deleteAd(id);
    }

    @DeleteMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAdByUser(@PathVariable (name = "userId") Integer userId){
        adService.deleteAdsByUser(userId);
    }
}
