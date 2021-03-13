package com.chronos.adservice.service.impl;

import com.chronos.adservice.dto.CategoryResponseDto;
import com.chronos.adservice.model.Category;
import com.chronos.adservice.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryServiceTest {

    @MockBean
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    private Category category;
    private CategoryResponseDto categoryResponseDto;

    @BeforeEach
    void setUp() {
        category = new Category("name","photo","icon");
        category.setId(1);
        categoryResponseDto = new CategoryResponseDto(1,"name","photo","icon");

    }

    @Test
    void findAll() {
      when(categoryRepository.findAll()).thenReturn(List.of(category));

      List<CategoryResponseDto> categoryResponseDtos = categoryService.getAllCategories();

      assertEquals(List.of(categoryResponseDto), categoryResponseDtos);

    }

}
