package com.chronos.userservice.controller.impl;

import com.chronos.userservice.controller.interfaces.IUserController;
import com.chronos.userservice.dto.UserRequestDto;
import com.chronos.userservice.dto.UserResponseDto;
import com.chronos.userservice.model.User;
import com.chronos.userservice.repository.UserRepository;
import com.chronos.userservice.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Optional;

@RestController
public class UserController implements IUserController {


    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDto findByUserById(@PathVariable (name = "id") Integer id){
        return userService.findById(id);
    }

    @GetMapping("/user/email/{email}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<User> findByEmail(@PathVariable (name = "email") String email){
        return userRepository.findByEmail(email);
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

    @DeleteMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable (name = "id") Integer id){
        userService.deleteUser(id);
    }

}
