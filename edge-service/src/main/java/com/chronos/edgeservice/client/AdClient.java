package com.chronos.edgeservice.client;

import com.chronos.edgeservice.apiresponse.ad.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient("ad-service")
public interface AdClient {

    @GetMapping("/ads")
    @ResponseStatus(HttpStatus.OK)
    public List<AdResponseDto> getAllAds ();

    @GetMapping("/ads/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<AdResponseDto> getAdsByUserId (@PathVariable (name = "id") Integer userId);

    @GetMapping("/ads/{status}")
    @ResponseStatus(HttpStatus.OK)
    public List<AdResponseDto> getAdsByStatus (@PathVariable (name = "status") String status);
    @GetMapping("/ad/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AdResponseDto getAdById (@PathVariable (name = "id") Integer id);

    @PostMapping("/ad")
    @ResponseStatus(HttpStatus.CREATED)
    public AdResponseDto createAd (@RequestBody @Valid AdRequestDto adRequestDto);

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<AdResponseDto> filter (@RequestParam (name = "word",required = false) String word, @RequestParam (name = "categoryId",required = false) Integer categoryId);


    @PutMapping("/ad/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void editAd(@PathVariable (name = "id") Integer id, @RequestBody @Valid AdRequestDto adRequestDto);

    @DeleteMapping("/ad/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAd(@PathVariable (name = "id") Integer id);

    @GetMapping("/categories")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryResponseDto> getAllCategories();

    @DeleteMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAdByUser(@PathVariable (name = "userId") Integer userId);

    @GetMapping("/favorite/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<FavoriteResponseDto> getAllFavoriteByUser(@PathVariable(name ="userId" ) Integer userId);

    @PostMapping("/favorite")
    @ResponseStatus(HttpStatus.CREATED)
    public void createFavorite(@RequestBody FavoriteRequestDto favoriteRequestDto);

    @DeleteMapping("/favorites/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFavorites(@PathVariable(name = "id") FavoriteRequestDto favoriteRequestDto);

    @DeleteMapping("/favorites/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFavorites(@PathVariable(name ="userId" ) Integer userId);
}
