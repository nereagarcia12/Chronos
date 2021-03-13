package com.chronos.adservice.controller.impl;


import com.chronos.adservice.apiresponse.UserResponseDto;
import com.chronos.adservice.dto.AdRequestDto;
import com.chronos.adservice.dto.AdResponseDto;

import com.chronos.adservice.dto.FilterSearchRequestDto;
import com.chronos.adservice.enums.Status;
import com.chronos.adservice.model.Ad;
import com.chronos.adservice.model.Category;
import com.chronos.adservice.service.impl.AdService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.List;


@SpringBootTest
class AdControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private AdService adService;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private Ad ad;
    private Ad adWithoutId;
    private AdRequestDto adRequestDto;
    private AdResponseDto adResponseDto;
    private UserResponseDto userResponseDto;
    private Category category;
    private FilterSearchRequestDto filterSearchRequestDto;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
        ad = new Ad("title","description","availability", LocalDate.now(),1,category, Status.ACTIVE);
        ad.setId(1);
        adWithoutId = new Ad("title","description","availability", LocalDate.now(),1,category, Status.ACTIVE);
        adRequestDto = new AdRequestDto("title","description","availability",1,1);
        adResponseDto = new AdResponseDto(1,"title","description","availability",LocalDate.now(),1,"name","photo");
        userResponseDto = new UserResponseDto(1,"name","email","phone","city",LocalDate.now(),5,false);
        filterSearchRequestDto = new FilterSearchRequestDto("title", 1);

    }

    @Test
    void getAllAds() throws Exception {
        String expectedJson = "[{\"id\":1,\"title\":\"title\",\"description\":\"description\",\"availability\":\"availability\",\"createAd\":\"2021-03-13\",\"userId\":1,\"categoryName\":\"name\",\"categoryImage\":\"photo\"}]";
        when(adService.findAll()).thenReturn(List.of(adResponseDto));

        MvcResult result = mockMvc
                .perform(get("/ads"))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(expectedJson, result.getResponse().getContentAsString());

    }

    @Test
    void getAdsByUserId() throws Exception {
        String expectedJson ="[{\"id\":1,\"title\":\"title\",\"description\":\"description\",\"availability\":\"availability\",\"createAd\":\"2021-03-13\",\"userId\":1,\"categoryName\":\"name\",\"categoryImage\":\"photo\"}]";
        when(adService.adsByUser(1)).thenReturn(List.of(adResponseDto));

        MvcResult result = mockMvc
                .perform(get("/ads/user/1"))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(expectedJson, result.getResponse().getContentAsString());
    }

    @Test
    void getAdsByStatus() throws Exception {
        String expectedJson = "[{\"id\":1,\"title\":\"title\",\"description\":\"description\",\"availability\":\"availability\",\"createAd\":\"2021-03-13\",\"userId\":1,\"categoryName\":\"name\",\"categoryImage\":\"photo\"}]";
        when(adService.findByStatus(Status.ACTIVE)).thenReturn(List.of(adResponseDto));

        MvcResult result = mockMvc
                .perform(get("/ads/active"))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(expectedJson, result.getResponse().getContentAsString());
    }

    @Test
    void getAdById() throws Exception {
        String expectedJson = "{\"id\":1,\"title\":\"title\",\"description\":\"description\",\"availability\":\"availability\",\"createAd\":\"2021-03-13\",\"userId\":1,\"categoryName\":\"name\",\"categoryImage\":\"photo\"}";
        when(adService.adById(1)).thenReturn(adResponseDto);

        MvcResult result = mockMvc
                .perform(get("/ad/1"))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(expectedJson, result.getResponse().getContentAsString());
    }

    @Test
    void createAd() throws Exception {
        when(adService.createAd(adRequestDto)).thenReturn(adResponseDto);

        String body = objectMapper.writeValueAsString(adRequestDto);

        MvcResult result =  mockMvc
                .perform(post("/ad")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        assertEquals("{\"id\":1,\"title\":\"title\",\"description\":\"description\",\"availability\":\"availability\",\"createAd\":\"2021-03-13\",\"userId\":1,\"categoryName\":\"name\",\"categoryImage\":\"photo\"}", result.getResponse().getContentAsString());
    }

    @Test
    void filter() throws Exception {
        String expectedJson = "[{\"id\":1,\"title\":\"title\",\"description\":\"description\",\"availability\":\"availability\",\"createAd\":\"2021-03-13\",\"userId\":1,\"categoryName\":\"name\",\"categoryImage\":\"photo\"}]";
        when(adService.search(new FilterSearchRequestDto(null, null))).thenReturn(List.of(adResponseDto));

        MvcResult result = mockMvc
                .perform(get("/search"))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(expectedJson, result.getResponse().getContentAsString());

    }

    @Test
    void editAd() throws Exception {

        String body = objectMapper.writeValueAsString(adRequestDto);

        MvcResult result = mockMvc
                .perform(put("/ad/1")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        verify(adService).editAd(adRequestDto,1);
    }

    @Test
    void deleteAd() throws Exception {
        MvcResult result = mockMvc
                .perform(delete("/ad/1"))
                .andExpect(status().isOk())
                .andReturn();

        verify(adService).deleteAd(1);
    }

    @Test
    void deleteAdByUser() throws Exception {
        MvcResult result = mockMvc
                .perform(delete("/user/1"))
                .andExpect(status().isOk())
                .andReturn();

        verify(adService).deleteAdsByUser(1);
    }

}
