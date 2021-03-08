package com.chronos.edgeservice.security.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class User {

    private Integer id;
    private String name;
    private String email;
    private String phone;
    private String city;
    private String password;
    private LocalDate createdAt;
    private Integer balanceHour;
    private Boolean pendingTransaction;
    private Status status;
    private Set<Role> roles = new HashSet<>();


    public User() {
    }

    public User(Integer id, String name, String email, String phone, String city, String password, LocalDate createdAt, Integer balanceHour, Boolean pendingTransaction, Status status, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.city = city;
        this.password = password;
        this.createdAt = createdAt;
        this.balanceHour = balanceHour;
        this.pendingTransaction = pendingTransaction;
        this.status = status;
        this.roles = roles;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
