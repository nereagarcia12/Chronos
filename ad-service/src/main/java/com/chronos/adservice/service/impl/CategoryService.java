package com.chronos.adservice.service.impl;

import com.chronos.adservice.dto.CategoryResponseDto;
import com.chronos.adservice.model.Category;
import com.chronos.adservice.repository.CategoryRepository;
import com.chronos.adservice.service.interfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryResponseDto> getAllCategories (){
        return categoryRepository.findAll().stream().map(category -> category.toResponseDto()).collect(Collectors.toList());
    }


}
