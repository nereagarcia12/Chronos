package com.chronos.adservice.dto;

import com.chronos.adservice.model.Ad;


import java.util.List;

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

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
