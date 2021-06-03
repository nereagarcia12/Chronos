package com.chronos.adservice.dto;

import javax.validation.constraints.NotNull;

public class FavoriteRequestDto {

    @NotNull(message = "the adId is required")
    private Integer adId;
    @NotNull(message = "the userId is required")
    private Integer userId;

    public FavoriteRequestDto(@NotNull(message = "the adId is required") Integer adId, @NotNull(message = "the userId is required") Integer userId) {
        this.adId = adId;
        this.userId = userId;
    }

    public Integer getAdId() {
        return adId;
    }

    public void setAdId(Integer adId) {
        this.adId = adId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
