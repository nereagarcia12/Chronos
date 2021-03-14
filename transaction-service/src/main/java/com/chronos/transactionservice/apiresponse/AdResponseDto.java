package com.chronos.transactionservice.apiresponse;


import java.time.LocalDate;

public class AdResponseDto {

    private Integer id;
    private String title;
    private String description;
    private String availability;
    private LocalDate createAd;
    private Integer userId;
    private String categoryName;

    public AdResponseDto(Integer id, String title, String description, String availability, LocalDate createAd, Integer userId, String categoryName) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.availability = availability;
        this.createAd = createAd;
        this.userId = userId;
        this.categoryName = categoryName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAvailability() {
        return availability;
    }

    public LocalDate getCreateAd() {
        return createAd;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getCategoryName() {
        return categoryName;
    }

}
