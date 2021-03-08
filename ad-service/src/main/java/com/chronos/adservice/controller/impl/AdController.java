package com.chronos.adservice.controller.impl;

import com.chronos.adservice.controller.interfaces.IAdController;
import com.chronos.adservice.dto.AdRequestDto;
import com.chronos.adservice.dto.AdResponseDto;
import com.chronos.adservice.dto.FilterSearchDto;
import com.chronos.adservice.enums.Status;
import com.chronos.adservice.model.Ad;
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
    public List<Ad> getAllAds (){
        return adService.findAll();
    }

    @GetMapping("/ads/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Ad> getAdsByUserId (@PathVariable (name = "id") Integer userId){
        return adService.adsByUser(userId);
    }

    @GetMapping("/ads/{status}")
    @ResponseStatus(HttpStatus.OK)
    public List<Ad> getAdsByStatus (@PathVariable (name = "status") String status){
        return adService.findByStatus(Status.valueOf(status.toLowerCase()));
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
    public List<AdResponseDto> filter (@RequestParam (name = "word") String word, @RequestParam (name = "categoryId") Integer categoryId){
        return adService.search(new FilterSearchDto(word, categoryId));
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

}
