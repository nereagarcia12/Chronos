package com.chronos.transactionservice.dto;

import java.util.Objects;

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

    public Integer getReceiverUserId() {
        return receiverUserId;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public Integer getAdId() {
        return adId;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionResponseDto that = (TransactionResponseDto) o;
        return Objects.equals(id, that.id) && Objects.equals(originUserId, that.originUserId) && Objects.equals(receiverUserId, that.receiverUserId) && Objects.equals(amount, that.amount) && Objects.equals(description, that.description) && Objects.equals(adId, that.adId) && Objects.equals(status, that.status);
    }

}
