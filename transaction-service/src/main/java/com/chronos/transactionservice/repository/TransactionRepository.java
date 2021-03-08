package com.chronos.transactionservice.repository;

import com.chronos.transactionservice.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    public List<Transaction> findByOriginUserIdOrReceiverUserId(Integer originUserId, Integer receiverUserId);

}
