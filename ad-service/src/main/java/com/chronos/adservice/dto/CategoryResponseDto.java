package com.chronos.adservice.dto;

import com.chronos.adservice.model.Ad;


import java.util.List;
import java.util.Objects;

public class CategoryResponseDto {

    private Integer id;
    private String name;
    private String photo;
    private String icon;

    public CategoryResponseDto() {
    }

    public CategoryResponseDto(Integer id, String name, String photo, String icon) {
        this.id = id;
        this.name = name;
        this.photo = photo;
        this.icon = icon;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoto() {
        return photo;
    }

    public String getIcon() {
        return icon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryResponseDto that = (CategoryResponseDto) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(photo, that.photo) && Objects.equals(icon, that.icon);
    }
}
