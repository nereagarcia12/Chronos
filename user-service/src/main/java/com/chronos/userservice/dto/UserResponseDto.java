package com.chronos.userservice.dto;

import java.time.LocalDate;
import java.util.Objects;

public class UserResponseDto {

    private Integer id;
    private String name;
    private String email;
    private String phone;
    private String city;
    private LocalDate createdAt;
    private Integer balanceHour;
    private Boolean pendingTransaction;


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

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getCity() {
        return city;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public Integer getBalanceHour() {
        return balanceHour;
    }

    public Boolean getPendingTransaction() {
        return pendingTransaction;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserResponseDto that = (UserResponseDto) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(phone, that.phone) && Objects.equals(city, that.city) && Objects.equals(createdAt, that.createdAt) && Objects.equals(balanceHour, that.balanceHour) && Objects.equals(pendingTransaction, that.pendingTransaction);
    }

}
