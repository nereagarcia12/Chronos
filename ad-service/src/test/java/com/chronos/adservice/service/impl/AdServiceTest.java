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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class AdServiceTest {

    @MockBean
    private UserClient userClient;

    @MockBean
    private AdRepository adRepository;

    @MockBean
    private CategoryRepository categoryRepository;

    @Autowired
    private AdService adService;

    private Ad ad;
    private Ad adWithoutId;
    private AdRequestDto adRequestDto;
    private AdResponseDto adResponseDto;
    private UserResponseDto userResponseDto;
    private Category category;
    private FilterSearchRequestDto filterSearchRequestDto;

    @BeforeEach
    void setUp() {
        category = new Category("name","photo","icon");
    category.setId(1);
       ad = new Ad("title","description","availability", LocalDate.now(),1,category, Status.ACTIVE);
       ad.setId(1);
       adWithoutId = new Ad("title","description","availability", LocalDate.now(),1,category, Status.ACTIVE);
       adRequestDto = new AdRequestDto("title","description","availability",1,1);
       adResponseDto = new AdResponseDto(1,"title","description","availability",LocalDate.now(),1,"name","photo");
       userResponseDto = new UserResponseDto(1,"name","email","phone","city",LocalDate.now(),5,false);
       filterSearchRequestDto = new FilterSearchRequestDto("title", 1);
    }

    @Test
    void findAll() {
        when(adRepository.findAll()).thenReturn(List.of(ad));

        List<AdResponseDto> adResponseDtos = adService.findAll();

        assertEquals(List.of(adResponseDto), adResponseDtos);
    }

    @Test
    void findByStatus() {
        when(adRepository.findByStatus(Status.ACTIVE)).thenReturn(List.of(ad));

        List<AdResponseDto> adResponseDtos = adService.findByStatus(Status.ACTIVE);

        assertEquals(List.of(adResponseDto), adResponseDtos);
    }

    @Test
    void adsByUser() {
        when(adRepository.findByUserId(1)).thenReturn(List.of(ad));

        List<AdResponseDto> adResponseDtos = adService.adsByUser(1);

        assertEquals(List.of(adResponseDto), adResponseDtos);
    }


    @Test
    void adById() {
        when(adRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(ad));

        AdResponseDto adResponseDto1 = adService.adById(1);

        assertEquals(adResponseDto,adResponseDto1);

    }

    @Test
    void adById_Exception() {
        when(adRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(AdNotFoundException.class, () -> adService.adById(ad.getId()));
    }

    @Test
    void createAd() {
        when(userClient.findByUserById(1)).thenReturn(userResponseDto);
        when(categoryRepository.findById(1)).thenReturn(Optional.ofNullable(category));
        when(adRepository.save(adWithoutId)).thenReturn(ad);

        AdResponseDto adResponseDtoResponse = adService.createAd(adRequestDto);

        assertEquals(adResponseDto,adResponseDtoResponse);
    }

    @Test
    void createAd_Exception_Category() {
        when(userClient.findByUserById(1)).thenReturn(userResponseDto);
        when(categoryRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> adService.createAd(adRequestDto));

    }
    @Test
    void search() {
        when(adRepository.findByTitleContains("title")).thenReturn(List.of(ad));
        when(adRepository.findByCategory_Id(1)).thenReturn(List.of(ad));
        when(adRepository.findAll()).thenReturn(List.of(ad));

        List<AdResponseDto> adResponseDtos = adService.search(filterSearchRequestDto);

        assertEquals(List.of(adResponseDto), adResponseDtos);
    }
    @Test
    void search_Word() {
        when(adRepository.findByTitleContains("title")).thenReturn(List.of(ad));

        List<AdResponseDto> adResponseDtos = adService.search(new FilterSearchRequestDto("title",null));

        assertEquals(List.of(adResponseDto), adResponseDtos);
    }
    @Test
    void Category() {
        when(adRepository.findByCategory_Id(1)).thenReturn(List.of(ad));

        List<AdResponseDto> adResponseDtos = adService.search(new FilterSearchRequestDto(null,1));

        assertEquals(List.of(adResponseDto), adResponseDtos);
    }

    @Test
    void editAd() {
        when(adRepository.findById(1)).thenReturn(Optional.ofNullable(ad));
        when(categoryRepository.findById(1)).thenReturn(Optional.ofNullable(category));
        ad.setTitle("hola");
        ad.setDescription("hola");
        ad.setAvailability("que tal");

        adService.editAd(adRequestDto,1);

        verify(adRepository).save(ad);

    }

    @Test
    void deleteAd() {
        when(adRepository.findById(1)).thenReturn(Optional.ofNullable(ad));

        adService.deleteAd(1);

        verify(adRepository).delete(ad);

    }

    @Test
    void deleteAdsByUser() {
        when(adRepository.findByUserId(1)).thenReturn(List.of(ad));
        when(adRepository.findById(1)).thenReturn(Optional.ofNullable(ad));

        adService.deleteAdsByUser(1);

        verify(adRepository).delete(ad);

    }
}
