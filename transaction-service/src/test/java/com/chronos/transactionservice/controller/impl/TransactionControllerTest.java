package com.chronos.transactionservice.controller.impl;

import com.chronos.transactionservice.apiresponse.AdResponseDto;
import com.chronos.transactionservice.apiresponse.UserResponseDto;
import com.chronos.transactionservice.dto.TransactionRequestDto;
import com.chronos.transactionservice.dto.TransactionResponseDto;
import com.chronos.transactionservice.enums.Status;
import com.chronos.transactionservice.model.Transaction;
import com.chronos.transactionservice.service.impl.TransactionService;
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

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
class TransactionControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private TransactionService transactionService;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private Transaction transaction;
    private TransactionRequestDto transactionRequestDto;
    private TransactionResponseDto transactionResponseDto;
    private UserResponseDto userResponseDtoOrigin;
    private UserResponseDto userResponseDtoReceiver;
    private AdResponseDto adResponseDto;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
        transaction = new Transaction(1, 2, 5, "description", 1);
        transaction.setId(1);
        transactionRequestDto = new TransactionRequestDto(1, 2, 5, "description", 1);
        transactionResponseDto = new TransactionResponseDto(1, 1, 2, 5, "description", 1, "PENDING");
        userResponseDtoOrigin = new UserResponseDto(1, "name", "email", "phone", "city", LocalDate.now(), 5, false);
        userResponseDtoReceiver = new UserResponseDto(2, "name", "email", "phone", "city", LocalDate.now(), 5, false);
        adResponseDto = new AdResponseDto(1, "title", "description", "availability", LocalDate.now(), 1, "name");
    }

    @Test
    void createTransaction() throws Exception {

        String body = objectMapper.writeValueAsString(transactionRequestDto);

        MvcResult result = mockMvc
                .perform(post("/transaction")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        verify(transactionService).makeTransaction(transactionRequestDto);
    }

    @Test
    void acceptTransaction() throws Exception {
        String body = objectMapper.writeValueAsString("");

        MvcResult result = mockMvc
                .perform(put("/transaction/1/user/1/accept")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        verify(transactionService).acceptTransaction(1,1);
    }

    @Test
    void refuseTransaction() throws Exception {
        String body = objectMapper.writeValueAsString("");

        MvcResult result = mockMvc
                .perform(put("/transaction/1/user/1/refuse")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        verify(transactionService).refuseTransaction(1,1);
    }

    @Test
    void completeTransaction() throws Exception {
        String body = objectMapper.writeValueAsString("");

        MvcResult result = mockMvc
                .perform(put("/transaction/1/user/1/complete")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        verify(transactionService).completeTransaction(1,1);
    }

    @Test
    void getAllTransactionByUserId() throws Exception {
        String expectedJson = "[{\"id\":1,\"originUserId\":1,\"receiverUserId\":2,\"amount\":5,\"description\":\"description\",\"adId\":1,\"status\":\"PENDING\"}]";
        when(transactionService.getTransactionByUser(1)).thenReturn(List.of(transactionResponseDto));

        MvcResult result = mockMvc
                .perform(get("/transactions/1"))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(expectedJson, result.getResponse().getContentAsString());

    }

    @Test
    void deleteTransactionByUser() throws Exception {
        MvcResult result = mockMvc
                .perform(delete("/transactions/1"))
                .andExpect(status().isOk())
                .andReturn();

        verify(transactionService).deleteTransactionByUser(1);


    }
}
