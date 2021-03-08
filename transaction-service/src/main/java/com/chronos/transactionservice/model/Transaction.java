package com.chronos.transactionservice.model;

import com.chronos.transactionservice.dto.TransactionResponseDto;
import com.chronos.transactionservice.enums.Status;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer originUserId;
    private Integer receiverUserId;
    private Status status;
    private Integer amount;
    private String description;
    private Integer adId;

    public Transaction() {
    }

    public Transaction(Integer originUserId, Integer receiverUserId, Integer amount, String description, Integer adId) {
        this.originUserId = originUserId;
        this.receiverUserId = receiverUserId;
        this.status = Status.PENDING;
        this.amount = amount;
        this.description = description;
        this.adId = adId;
    }

    public TransactionResponseDto toResponseDto(){
        return new TransactionResponseDto(this.getId(),this.getOriginUserId(),this.getReceiverUserId(),this.getAmount(),this.getDescription(),this.getAdId(),this.getStatus().name());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOriginUserId() {
        return originUserId;
    }

    public void setOriginUserId(Integer originUserId) {
        this.originUserId = originUserId;
    }

    public Integer getReceiverUserId() {
        return receiverUserId;
    }

    public void setReceiverUserId(Integer receiverUserId) {
        this.receiverUserId = receiverUserId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAdId() {
        return adId;
    }

    public void setAdId(Integer adId) {
        this.adId = adId;
    }
}
