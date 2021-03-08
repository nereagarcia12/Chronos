package com.chronos.transactionservice.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TransactionResponseDto {

    private Integer id;
    private Integer originUserId;
    private Integer receiverUserId;
    private Integer amount;
    private String description;
    private Integer adId;
    private String status;

    public TransactionResponseDto() {
    }

    public TransactionResponseDto(Integer id, Integer originUserId, Integer receiverUserId, Integer amount, String description, Integer adId, String status) {
        this.id = id;
        this.originUserId = originUserId;
        this.receiverUserId = receiverUserId;
        this.amount = amount;
        this.description = description;
        this.adId = adId;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
