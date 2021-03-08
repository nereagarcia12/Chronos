package com.chronos.userservice.controller.impl;

import com.chronos.userservice.controller.interfaces.IUserController;
import com.chronos.userservice.dto.UserRequestDto;
import com.chronos.userservice.dto.UserResponseDto;
import com.chronos.userservice.model.User;
import com.chronos.userservice.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
public class UserController implements IUserController {


    @Autowired
    private UserService userService;

    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDto findByUserById(@PathVariable (name = "id") Integer id){
        return userService.findById(id);
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody @Valid UserRequestDto userRequestDto){
        userService.createUser(userRequestDto);
    }

    @PutMapping("/user/{id}/increase/balance")
    @ResponseStatus(HttpStatus.OK)
    public void increaseBalanceHours(@PathVariable (name = "id") Integer id, @RequestBody @Valid @Min(value = 0,message = "i can't less than 0") Integer amount){
    userService.increaseBalance(id, amount);
    }

    @PutMapping("/user/{id}/decrease/balance")
    @ResponseStatus(HttpStatus.OK)
    public void decreaseBalanceHours(@PathVariable (name = "id") Integer id, @RequestBody @Valid @Min(value = 0,message = "i can't less than 0") Integer amount){
        userService.decreaseBalance(id,amount);
    }

    @PutMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void editProfileUser(@PathVariable (name = "id") Integer id, @RequestBody @Valid UserRequestDto userRequestDto){
        userService.editUser(userRequestDto, id);
    }

    @DeleteMapping("user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable (name = "id") Integer id){
        userService.deleteUser(id);
    }

}
