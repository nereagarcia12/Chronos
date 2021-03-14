package com.chronos.transactionservice.dto;

import com.chronos.transactionservice.enums.Status;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class TransactionRequestDto {

    @NotNull(message = "the OriginUser is required")
    private Integer originUserId;
    @NotNull(message = "the ReceiverUser is required")
    private Integer receiverUserId;
    @NotNull(message = "the OriginUser is required")
    @Min(value = 1,message = "the amount must be greater than 1")
    private Integer amount;
    @NotEmpty(message = "the description is required")
    private String description;
    @NotNull(message = "the adId is required")
    private Integer adId;

    public TransactionRequestDto() {
    }

    public TransactionRequestDto(@NotNull(message = "the OriginUser is required") Integer originUserId, @NotNull(message = "the ReceiverUser is required") Integer receiverUserId, @NotNull(message = "the OriginUser is required") @Min(value = 1, message = "the amount must be greater than 1") Integer amount, @NotEmpty(message = "the description is required") String description, @NotNull(message = "the adId is required") Integer adId) {
        this.originUserId = originUserId;
        this.receiverUserId = receiverUserId;
        this.amount = amount;
        this.description = description;
        this.adId = adId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionRequestDto that = (TransactionRequestDto) o;
        return Objects.equals(originUserId, that.originUserId) && Objects.equals(receiverUserId, that.receiverUserId) && Objects.equals(amount, that.amount) && Objects.equals(description, that.description) && Objects.equals(adId, that.adId);
    }

}
