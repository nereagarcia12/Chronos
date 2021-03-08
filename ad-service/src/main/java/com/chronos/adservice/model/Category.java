package com.chronos.adservice.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String photo;

    @OneToMany(mappedBy = "category")
    private List<Ad> ads;

    public Category() {
    }

    public Category(String name, String photo) {
        this.name = name;
        this.photo = photo;
    }

    public Category(String name, String photo, List<Ad> ads) {
        this.name = name;
        this.photo = photo;
        this.ads = ads;
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

    public List<Ad> getAds() {
        return ads;
    }

    public void setAds(List<Ad> ads) {
        this.ads = ads;
    }
}
