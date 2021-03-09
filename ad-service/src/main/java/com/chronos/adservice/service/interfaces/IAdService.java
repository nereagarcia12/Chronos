package com.chronos.adservice.service.interfaces;


import com.chronos.adservice.dto.AdRequestDto;
import com.chronos.adservice.dto.AdResponseDto;
import com.chronos.adservice.dto.FilterSearchDto;
import com.chronos.adservice.enums.Status;
import com.chronos.adservice.model.Ad;


import java.util.List;


public interface IAdService {

    public List<AdResponseDto> findAll() ;

    public List<AdResponseDto> findByStatus(Status status) ;
    public List<AdResponseDto> adsByUser(Integer id);

    public AdResponseDto adById(Integer id);

    public AdResponseDto createAd(AdRequestDto adRequestDto) ;
    public List<AdResponseDto> search(FilterSearchDto filterSearchDto);

    public void editAd(AdRequestDto adRequestDto, Integer id);


    public void deleteAd(Integer id);
}
