package com.chronos.transactionservice.service.impl;

import com.chronos.transactionservice.apiresponse.AdResponseDto;
import com.chronos.transactionservice.apiresponse.UserResponseDto;
import com.chronos.transactionservice.client.AdClient;
import com.chronos.transactionservice.client.UserClient;
import com.chronos.transactionservice.dto.TransactionRequestDto;
import com.chronos.transactionservice.dto.TransactionResponseDto;
import com.chronos.transactionservice.enums.Status;
import com.chronos.transactionservice.exception.TransactionNotOwnedByUserException;
import com.chronos.transactionservice.exception.UnexpectedTransactionStatusException;
import com.chronos.transactionservice.model.Transaction;
import com.chronos.transactionservice.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class TransactionServiceTest {

    @MockBean
    private UserClient userClient;

    @MockBean
    private AdClient adClient;

    @MockBean
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionService transactionService;

    private Transaction transaction;
    private TransactionRequestDto transactionRequestDto;
    private TransactionResponseDto transactionResponseDto;
    private UserResponseDto userResponseDtoOrigin;
    private UserResponseDto userResponseDtoReceiver;
    private AdResponseDto adResponseDto;

    @BeforeEach
    void setUp() {
        transaction = new Transaction(1, 2, 5, "description", 1);
        transaction.setId(1);
        transactionRequestDto = new TransactionRequestDto(1, 2, 5, "description", 1);
        transactionResponseDto = new TransactionResponseDto(1, 1, 2, 5, "description", 1, "PENDING");
        userResponseDtoOrigin = new UserResponseDto(1, "name", "email", "phone", "city", LocalDate.now(), 5, false);
        userResponseDtoReceiver = new UserResponseDto(2, "name", "email", "phone", "city", LocalDate.now(), 5, false);
        adResponseDto = new AdResponseDto(1, "title", "description", "availability", LocalDate.now(), 1, "name");
    }

    @Test
    void makeTransaction() {
        transaction.setId(null);
        when(userClient.findByUserById(transaction.getOriginUserId())).thenReturn(userResponseDtoOrigin);
        when(userClient.findByUserById(transaction.getReceiverUserId())).thenReturn(userResponseDtoOrigin);
        when(adClient.getAdById(transaction.getAdId())).thenReturn(adResponseDto);
        userClient.decreaseBalanceHours(userResponseDtoOrigin.getId(), transactionRequestDto.getAmount());

        transactionService.makeTransaction(transactionRequestDto);

        verify(transactionRepository).save(transaction);
    }

    @Test
    void acceptTransaction() {
        when(transactionRepository.findById(1)).thenReturn(Optional.ofNullable(transaction));
        Transaction expectedTransaction = new Transaction(1, 2, 5, "description", 1);
        expectedTransaction.setId(1);
        expectedTransaction.setStatus(Status.ACCEPTED);

        transactionService.acceptTransaction(1, 2);

        verify(transactionRepository).save(expectedTransaction);
    }

    @Test
    void acceptTransaction_receivedNotOwned() {
        when(transactionRepository.findById(1)).thenReturn(Optional.ofNullable(transaction));

        assertThrows(TransactionNotOwnedByUserException.class, ()->
            transactionService.acceptTransaction(1, 4)
        );

        verify(transactionRepository, times(0)).save(any());
    }

    @Test
    void acceptTransaction_unexpectedTransactionStatusException() {
        when(transactionRepository.findById(1)).thenReturn(Optional.ofNullable(transaction));
        transaction.setStatus(Status.REFUSED);

        assertThrows(UnexpectedTransactionStatusException.class, ()->
                transactionService.acceptTransaction(1, 2)
        );

        verify(transactionRepository, times(0)).save(any());
    }

    @Test
    void refuseTransaction() {
        when(transactionRepository.findById(1)).thenReturn(Optional.ofNullable(transaction));
        Transaction expectedTransaction = new Transaction(1, 2, 5, "description", 1);
        expectedTransaction.setId(1);
        expectedTransaction.setStatus(Status.REFUSED);

        transactionService.refuseTransaction(1, 2);

        verify(transactionRepository).save(expectedTransaction);
    }

    @Test
    void completeTransaction() {
        transaction.setStatus(Status.ACCEPTED);
        when(transactionRepository.findById(1)).thenReturn(Optional.ofNullable(transaction));
        Transaction expectedTransaction = new Transaction(1, 2, 5, "description", 1);
        expectedTransaction.setId(1);
        expectedTransaction.setStatus(Status.COMPLETED);

        transactionService.completeTransaction(1, 2);

        verify(transactionRepository).save(expectedTransaction);
    }

    @Test
    void getTransactionByUser() {

        when(transactionRepository.findByOriginUserIdOrReceiverUserId(1,1)).thenReturn(List.of(transaction));

        List<TransactionResponseDto> transactionResponseDtos = transactionService.getTransactionByUser(1);

        assertEquals(List.of(transactionResponseDto), transactionResponseDtos);
    }

    @Test
    void deleteTransactionByUser() {
    when(transactionRepository.findByOriginUserIdOrReceiverUserId(1,1)).thenReturn(List.of(transaction));

    transactionService.deleteTransactionByUser(1);

    verify(transactionRepository).delete(transaction);

    }
}
