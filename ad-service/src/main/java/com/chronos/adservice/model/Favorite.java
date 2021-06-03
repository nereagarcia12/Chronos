package com.chronos.adservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Favorite {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer adId;
    private Integer userId;

    public Favorite() {
    }

    public Favorite(Integer adId, Integer userId) {
        this.adId = adId;
        this.userId = userId;
    }

    public Favorite(Integer id, Integer adId, Integer userId) {
        this.id = id;
        this.adId = adId;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
