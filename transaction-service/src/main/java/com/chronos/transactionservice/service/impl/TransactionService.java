package com.chronos.transactionservice.service.impl;

import com.chronos.transactionservice.apiresponse.AdResponseDto;
import com.chronos.transactionservice.apiresponse.UserResponseDto;
import com.chronos.transactionservice.client.AdClient;
import com.chronos.transactionservice.client.UserClient;
import com.chronos.transactionservice.dto.TransactionRequestDto;
import com.chronos.transactionservice.dto.TransactionResponseDto;
import com.chronos.transactionservice.enums.Status;
import com.chronos.transactionservice.exception.TransactionNotFoundException;
import com.chronos.transactionservice.exception.TransactionNotOwnedByUserException;
import com.chronos.transactionservice.exception.UnexpectedTransactionStatusException;
import com.chronos.transactionservice.model.Transaction;
import com.chronos.transactionservice.repository.TransactionRepository;
import com.chronos.transactionservice.service.interfaces.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService implements ITransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AdClient adClient;
    @Autowired
    private UserClient userClient;

    public void makeTransaction(TransactionRequestDto transactionRequestDto) {
        UserResponseDto userOrigin = userClient.findByUserById(transactionRequestDto.getOriginUserId());
        UserResponseDto userReceiver = userClient.findByUserById(transactionRequestDto.getReceiverUserId());
        AdResponseDto ad = adClient.getAdById(transactionRequestDto.getAdId());
        userClient.decreaseBalanceHours(userOrigin.getId(), transactionRequestDto.getAmount());


        transactionRepository.save(new Transaction(transactionRequestDto.getOriginUserId(), transactionRequestDto.getReceiverUserId(), transactionRequestDto.getAmount(), transactionRequestDto.getDescription(), transactionRequestDto.getAdId()));
    }

    public void acceptTransaction(Integer id, Integer userId) {
        Transaction transaction = getTransaction(id);
        receiverOwnedTransaction(userId, transaction);
        haveCorrectStatus(transaction, Status.PENDING);

        transaction.setStatus(Status.ACCEPTED);
        transactionRepository.save(transaction);
    }

    public void refuseTransaction(Integer id, Integer userId) {
        Transaction transaction = getTransaction(id);
        receiverOwnedTransaction(userId, transaction);
        haveCorrectStatus(transaction, Status.PENDING);

        transaction.setStatus(Status.REFUSED);
        transactionRepository.save(transaction);
        userClient.increaseBalanceHours(userId, transaction.getAmount());
    }

    public void completeTransaction(Integer id, Integer userId) {
        Transaction transaction = getTransaction(id);
        receiverOwnedTransaction(userId, transaction);
        haveCorrectStatus(transaction, Status.ACCEPTED);

        transaction.setStatus(Status.COMPLETED);
        userClient.increaseBalanceHours(userId, transaction.getAmount());
        transactionRepository.save(transaction);
    }

    public List<TransactionResponseDto> getTransactionByUser(Integer userId) {
        return transactionRepository.findByOriginUserIdOrReceiverUserId(userId, userId).stream().map(Transaction::toResponseDto).collect(Collectors.toList());
    }

    public void deleteTransactionByUser(Integer userId){
        transactionRepository.findByOriginUserIdOrReceiverUserId(userId, userId).forEach(transaction -> transactionRepository.delete(transaction));
    }

    private void haveCorrectStatus(Transaction transaction, Status accepted) {
        if (transaction.getStatus() != accepted) {
            throw new UnexpectedTransactionStatusException();
        }
    }

    private Transaction getTransaction(Integer id) {
        return transactionRepository.findById(id).orElseThrow(TransactionNotFoundException::new);
    }

    private void receiverOwnedTransaction(Integer userId, Transaction transaction) {
        if (!transaction.getReceiverUserId().equals(userId)) {
            throw new TransactionNotOwnedByUserException();
        }
    }
}
