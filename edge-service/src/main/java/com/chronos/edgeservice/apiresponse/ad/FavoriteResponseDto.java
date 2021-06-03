package com.chronos.edgeservice.apiresponse.ad;

public class FavoriteResponseDto {

    private Integer adId;
    private Integer userId;


    public FavoriteResponseDto() {
    }

    public FavoriteResponseDto(Integer adId, Integer userId) {
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
