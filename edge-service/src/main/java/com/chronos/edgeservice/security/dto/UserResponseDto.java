package com.chronos.edgeservice.security.dto;

import java.time.LocalDate;

public class UserResponseDto {

    private Integer id;
    private String name;
    private String email;
    private String phone;
    private String city;
    private LocalDate createdAt;
    private Integer balanceHour;
    private Boolean pendingTransaction;

    public UserResponseDto() {
    }

    public UserResponseDto(Integer id, String name, String email, String phone, String city, LocalDate createdAt, Integer balanceHour, Boolean pendingTransaction) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.city = city;
        this.createdAt = createdAt;
        this.balanceHour = balanceHour;
        this.pendingTransaction = pendingTransaction;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getBalanceHour() {
        return balanceHour;
    }

    public void setBalanceHour(Integer balanceHour) {
        this.balanceHour = balanceHour;
    }

    public Boolean getPendingTransaction() {
        return pendingTransaction;
    }

    public void setPendingTransaction(Boolean pendingTransaction) {
        this.pendingTransaction = pendingTransaction;
    }
}
