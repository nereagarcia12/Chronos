package com.chronos.edgeservice.security;


import javax.validation.constraints.NotEmpty;
import java.util.Objects;

public class UserRequestDto {
    @NotEmpty(message = "the name is required")
    private String name;
    @NotEmpty(message = "the email is required")
    private String email;
    @NotEmpty(message = "the phone is required")
    private String phone;
    @NotEmpty(message = "the city is required")
    private String city;
    @NotEmpty(message = "the password is required")
    private String password;


    public UserRequestDto(@NotEmpty(message = "the name is required") String name, @NotEmpty(message = "the email is required") String email, @NotEmpty(message = "the phone is required") String phone, @NotEmpty(message = "the city is required") String city, @NotEmpty(message = "the password is required") String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.city = city;
        this.password = password;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRequestDto that = (UserRequestDto) o;
        return Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(phone, that.phone) && Objects.equals(city, that.city) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, phone, city, password);
    }
}
