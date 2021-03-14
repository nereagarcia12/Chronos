package com.chronos.transactionservice.handler;

import com.chronos.transactionservice.apiresponse.AdResponseDto;
import com.chronos.transactionservice.apiresponse.UserResponseDto;
import com.chronos.transactionservice.dto.TransactionRequestDto;
import com.chronos.transactionservice.dto.TransactionResponseDto;
import com.chronos.transactionservice.exception.TransactionNotFoundException;
import com.chronos.transactionservice.exception.TransactionNotOwnedByUserException;
import com.chronos.transactionservice.exception.UnexpectedTransactionStatusException;
import com.chronos.transactionservice.model.Transaction;
import com.chronos.transactionservice.service.impl.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ApiExceptionHandlerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private TransactionService transactionService;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private Transaction transaction;
    private TransactionRequestDto transactionRequestDto;
    private TransactionResponseDto transactionResponseDto;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
        transaction = new Transaction(1, 2, 5, "description", 1);
        transaction.setId(1);
        transactionRequestDto = new TransactionRequestDto(1, 2, 5, "description", 1);
        transactionResponseDto = new TransactionResponseDto(1, 1, 2, 5, "description", 1, "PENDING");
    }

    @Test
    void transactionNotFoundException() throws Exception {
        String expectedJson = "{\"error\":\"Transaction not Found\"}";
        TransactionNotFoundException exception = new TransactionNotFoundException();
        doThrow(exception).when(transactionService).getTransactionByUser(1);

        MvcResult result = mockMvc
                .perform(get("/transactions/1"))
                .andExpect(status().isNotFound())
                .andReturn();

        assertEquals(expectedJson, result.getResponse().getContentAsString());
    }

    @Test
    void transactionNotOwnedByUserException() throws Exception {
        String expectedJson = "{\"error\":\"Transaction not owned by user\"}";
        TransactionNotOwnedByUserException exception = new TransactionNotOwnedByUserException();
        doThrow(exception).when(transactionService).getTransactionByUser(1);

        MvcResult result = mockMvc
                .perform(get("/transactions/1"))
                .andExpect(status().isForbidden())
                .andReturn();

        assertEquals(expectedJson, result.getResponse().getContentAsString());
    }

    @Test
    void unexpectedTransactionStatusException() throws Exception {
        String expectedJson = "{\"error\":\"Unexpected Transaction Status\"}";
        UnexpectedTransactionStatusException exception = new UnexpectedTransactionStatusException();
        doThrow(exception).when(transactionService).getTransactionByUser(1);

        MvcResult result = mockMvc
                .perform(get("/transactions/1"))
                .andExpect(status().isPreconditionFailed())
                .andReturn();

        assertEquals(expectedJson, result.getResponse().getContentAsString());
    }

    @Test
    void feignException() {
    }
}
