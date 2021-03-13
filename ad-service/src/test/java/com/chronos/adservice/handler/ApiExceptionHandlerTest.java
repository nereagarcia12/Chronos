package com.chronos.adservice.handler;

import com.chronos.adservice.dto.CategoryResponseDto;
import com.chronos.adservice.exceptions.AdNotFoundException;
import com.chronos.adservice.exceptions.CategoryNotFoundException;
import com.chronos.adservice.service.impl.CategoryService;
import feign.FeignException;
import feign.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ApiExceptionHandlerTest {


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
    void noPresentCategory_correctly_handled() throws Exception {
        String expectedJson = "{\"error\":\"Category is not present\"}";
        CategoryNotFoundException exception = new CategoryNotFoundException();
        doThrow(exception).when(categoryService).getAllCategories();

        MvcResult result = mockMvc
                .perform(get("/categories"))
                .andExpect(status().isNotFound())
                .andReturn();

        assertEquals(expectedJson, result.getResponse().getContentAsString());
    }

    @Test
    void noPresentAd_correctly_handled() throws Exception {
        String expectedJson = "{\"error\":\"Ad is not present\"}";
        AdNotFoundException exception = new AdNotFoundException();
        doThrow(exception).when(categoryService).getAllCategories();

        MvcResult result = mockMvc
                .perform(get("/categories"))
                .andExpect(status().isNotFound())
                .andReturn();

        assertEquals(expectedJson, result.getResponse().getContentAsString());
    }
}
