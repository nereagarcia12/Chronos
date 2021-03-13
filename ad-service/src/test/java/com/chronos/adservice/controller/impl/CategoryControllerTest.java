package com.chronos.adservice.controller.impl;

import com.chronos.adservice.dto.CategoryResponseDto;
import com.chronos.adservice.service.impl.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.mockito.Mockito.when;



import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class CategoryControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private CategoryService categoryService;

    private MockMvc mockMvc;

    private CategoryResponseDto categoryResponseDto;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();

        categoryResponseDto = new CategoryResponseDto(1,"name","photo","icon");

    }


    @Test
    void getAllCategories() throws Exception {
        String expectedJson = "[{\"id\":1,\"name\":\"name\",\"photo\":\"photo\",\"icon\":\"icon\"}]";
        when(categoryService.getAllCategories()).thenReturn(List.of(categoryResponseDto));

        MvcResult result = mockMvc
                .perform(get("/categories"))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(expectedJson, result.getResponse().getContentAsString());
    }
}
