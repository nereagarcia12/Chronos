package com.chronos.edgeservice.controller;

import com.chronos.edgeservice.client.UserClient;
import com.chronos.edgeservice.security.UserRequestDto;
import com.chronos.edgeservice.security.dto.User;
import com.chronos.edgeservice.security.dto.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserClient userClient;

    @GetMapping("/user/email/{email}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<User> findByEmail(@PathVariable(name = "email") String email){
        return userClient.findByEmail(email);
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody @Valid UserRequestDto userRequestDto){
        userClient.createUser(userRequestDto);
    }

    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDto findByUserById(@PathVariable (name = "id") Integer id){
        return userClient.findByUserById(id);
    }

    @PutMapping("/user/{id}/increase/balance")
    @ResponseStatus(HttpStatus.OK)
    public void increaseBalanceHours(@PathVariable (name = "id") Integer id, @RequestBody @Valid @Min(value = 0,message = "i can't less than 0") Integer amount){
        userClient.increaseBalanceHours(id,amount);
    }

    @PutMapping("/user/{id}/decrease/balance")
    @ResponseStatus(HttpStatus.OK)
    public void decreaseBalanceHours(@PathVariable (name = "id") Integer id, @RequestBody @Valid @Min(value = 0,message = "i can't less than 0") Integer amount){
        userClient.decreaseBalanceHours(id,amount);
    }

    @PutMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void editProfileUser(@PathVariable (name = "id") Integer id, @RequestBody @Valid UserRequestDto userRequestDto){
        userClient.editProfileUser(id, userRequestDto);
    }

    @DeleteMapping("user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable (name = "id") Integer id){
        userClient.deleteUser(id);
    }




}
