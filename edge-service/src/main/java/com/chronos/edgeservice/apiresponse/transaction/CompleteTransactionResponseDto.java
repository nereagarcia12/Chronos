package com.chronos.edgeservice.apiresponse.transaction;

import com.chronos.edgeservice.apiresponse.ad.AdResponseDto;
import com.chronos.edgeservice.security.dto.UserResponseDto;

public class CompleteTransactionResponseDto {

    private Integer id;
    private UserResponseDto originUserId;
    private UserResponseDto receiverUserId;
    private Integer amount;
    private String description;
    private AdResponseDto adId;
    private String status;

    public CompleteTransactionResponseDto() {
    }

    public CompleteTransactionResponseDto(Integer id, UserResponseDto originUserId, UserResponseDto receiverUserId, Integer amount, String description, AdResponseDto adId, String status) {
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

    public UserResponseDto getOriginUserId() {
        return originUserId;
    }

    public void setOriginUserId(UserResponseDto originUserId) {
        this.originUserId = originUserId;
    }

    public UserResponseDto getReceiverUserId() {
        return receiverUserId;
    }

    public void setReceiverUserId(UserResponseDto receiverUserId) {
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

    public AdResponseDto getAdId() {
        return adId;
    }

    public void setAdId(AdResponseDto adId) {
        this.adId = adId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
