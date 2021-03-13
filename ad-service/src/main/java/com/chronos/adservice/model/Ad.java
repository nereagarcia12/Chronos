package com.chronos.adservice.model;

import com.chronos.adservice.dto.AdResponseDto;
import com.chronos.adservice.enums.Status;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;
    private String availability;
    private LocalDate createAd;
    private Integer userId;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @Enumerated(EnumType.STRING)
    private Status status;

    public Ad(String title, String description, String availability, LocalDate createAd, Integer userId, Category category, Status status) {
        this.title = title;
        this.description = description;
        this.availability = availability;
        this.createAd = createAd;
        this.userId = userId;
        this.category = category;
        this.status = status;
    }

    public Ad(String title, String description, String availability, Integer userId, Category category) {
        this.title = title;
        this.description = description;
        this.availability = availability;
        this.createAd = LocalDate.now();
        this.userId = userId;
        this.category = category;
        this.status = Status.ACTIVE;
    }


    public Ad() {
    }

    public AdResponseDto toResponseDto(){
        return new AdResponseDto(this.getId(), this.getTitle(), this.getDescription(), this.getAvailability(), this.getCreateAd(), this.getUserId(), this.getCategory().getName(), this.getCategory().getPhoto());
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ad ad = (Ad) o;
        return Objects.equals(id, ad.id) && Objects.equals(title, ad.title) && Objects.equals(description, ad.description) && Objects.equals(availability, ad.availability) && Objects.equals(createAd, ad.createAd) && Objects.equals(userId, ad.userId) && Objects.equals(category, ad.category) && status == ad.status;
    }

}
