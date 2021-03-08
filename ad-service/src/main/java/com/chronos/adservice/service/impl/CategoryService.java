package com.chronos.adservice.service.impl;

import com.chronos.adservice.model.Category;
import com.chronos.adservice.repository.CategoryRepository;
import com.chronos.adservice.service.interfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories (){
        return categoryRepository.findAll();
    }


}
