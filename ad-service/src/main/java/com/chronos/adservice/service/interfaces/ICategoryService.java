package com.chronos.adservice.service.interfaces;

import com.chronos.adservice.dto.CategoryResponseDto;
import com.chronos.adservice.model.Category;

import java.util.List;

public interface ICategoryService {
    public List<CategoryResponseDto> getAllCategories ();

}
