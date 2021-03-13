package com.chronos.adservice.dto;

import java.time.LocalDate;
import java.util.Objects;

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

    public String getCategoryImage() {
        return categoryImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdResponseDto that = (AdResponseDto) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(availability, that.availability) && Objects.equals(createAd, that.createAd) && Objects.equals(userId, that.userId) && Objects.equals(categoryName, that.categoryName) && Objects.equals(categoryImage, that.categoryImage);
    }
}
