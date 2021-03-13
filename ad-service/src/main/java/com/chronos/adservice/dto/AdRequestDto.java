package com.chronos.adservice.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

public class AdRequestDto {

    @NotEmpty(message = "the title is required")
    private String title;
    @NotEmpty(message = "the description is required")
    private String description;
    @NotEmpty(message = "the availability is required")
    private String availability;
    @NotNull(message = "the userId is required")
    private Integer userId;
    @NotNull(message = "the categoryId is required")
    private Integer categoryId;

    public AdRequestDto(@NotEmpty(message = "the title is required") String title, @NotEmpty(message = "the description is required") String description, @NotEmpty(message = "the availability is required") String availability, @NotNull(message = "the userId is required") Integer userId, @NotNull(message = "the categoryId is required") Integer categoryId) {
        this.title = title;
        this.description = description;
        this.availability = availability;
        this.userId = userId;
        this.categoryId = categoryId;
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

    public Integer getUserId() {
        return userId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdRequestDto adRequestDto = (AdRequestDto) o;
        return Objects.equals(title, adRequestDto.title) && Objects.equals(description, adRequestDto.description) && Objects.equals(availability, adRequestDto.availability) && Objects.equals(userId, adRequestDto.userId) && Objects.equals(categoryId, adRequestDto.categoryId);
    }
}
