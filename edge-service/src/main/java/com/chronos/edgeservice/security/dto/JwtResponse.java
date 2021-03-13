package com.chronos.edgeservice.security.dto;

import java.util.List;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private List<String> roles;
    private Integer balance;
    private String name;
    private boolean pendingTransaction;

    public JwtResponse(String accessToken, Long id, String username,List<String> roles, Integer balance, String name,boolean pendingTransaction) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.roles = roles;
        this.name = name;
        this.balance = balance;
        this.pendingTransaction = pendingTransaction;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public boolean isPendingTransaction() {
        return pendingTransaction;
    }

    public void setPendingTransaction(boolean pendingTransaction) {
        this.pendingTransaction = pendingTransaction;
    }
}
