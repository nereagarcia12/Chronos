package com.chronos.userservice.model;

import com.chronos.userservice.dto.UserResponseDto;
import com.chronos.userservice.enums.Status;
import com.chronos.userservice.exceptions.InsufficientHoursException;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "profile")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private String city;
    private String password;
    private LocalDate createdAt;
    private Integer balanceHour;
    private Boolean pendingTransaction;
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    public User() {
    }



    public User(String name, String email, String phone, String city, String password, LocalDate createdAt, Integer balanceHour, Boolean pendingTransaction, Status status) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.city = city;
        this.password = password;
        this.createdAt = createdAt;
        this.balanceHour = balanceHour;
        this.pendingTransaction = pendingTransaction;
        this.status = status;
    }

    public User(String name, String email, String phone, String city, String password, Set<Role>  role) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.city = city;
        this.password = password;
        this.createdAt = LocalDate.now();
        this.balanceHour = 5;
        this.pendingTransaction = false;
        this.status = Status.ACTIVE;
        this.roles = role;
    }

    public UserResponseDto toConvertDto(){
        return new UserResponseDto(this.getId(), this.getName(),this.getEmail(),this.getPhone(),this.getCity(),this.getCreatedAt(),this.getBalanceHour(),this.getPendingTransaction());
    }
    public void increaseBalance (Integer amount){
        this.balanceHour += amount;
    }

    public void decreaseBalance (Integer amount){
        if (amount > balanceHour){
            throw new InsufficientHoursException();
        }
        this.balanceHour -= amount;
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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public Integer getBalanceHour() {
        return balanceHour;
    }

    public Boolean getPendingTransaction() {
        return pendingTransaction;
    }

    public Status getStatus() {
        return status;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(phone, user.phone) && Objects.equals(city, user.city) && Objects.equals(password, user.password) && Objects.equals(createdAt, user.createdAt) && Objects.equals(balanceHour, user.balanceHour) && Objects.equals(pendingTransaction, user.pendingTransaction) && status == user.status && Objects.equals(roles, user.roles);
    }

}
