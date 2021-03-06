package com.chronos.edgeservice.apiresponse.ad;


import java.time.LocalDate;

public class AdResponseDto {

    private Integer id;
    private String title;
    private String description;
    private String availability;
    private LocalDate createAd;
    private Integer userId;
    private String categoryName;
    private String categoryImage;

    public AdResponseDto(Integer id, String title, String description, String availability, LocalDate createAd, Integer userId, String categoryName, String categoryImage) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.availability = availability;
        this.createAd = createAd;
        this.userId = userId;
        this.categoryName = categoryName;
        this.categoryImage = categoryImage;

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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public LocalDate getCreateAd() {
        return createAd;
    }

    public void setCreateAd(LocalDate createAd) {
        this.createAd = createAd;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(Integer categoryId) {
        this.categoryName = categoryName;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }
}
