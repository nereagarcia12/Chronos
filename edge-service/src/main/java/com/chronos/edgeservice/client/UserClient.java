package com.chronos.edgeservice.client;

import com.chronos.edgeservice.security.UserRequestDto;
import com.chronos.edgeservice.security.dto.User;
import com.chronos.edgeservice.security.dto.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

@FeignClient("user-service")
public interface UserClient {

    @GetMapping("/user/email/{email}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<User> findByEmail(@PathVariable(name = "email") String email);

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody @Valid UserRequestDto userRequestDto);

    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDto findByUserById(@PathVariable (name = "id") Integer id);

    @PutMapping("/user/{id}/increase/balance")
    @ResponseStatus(HttpStatus.OK)
    public void increaseBalanceHours(@PathVariable (name = "id") Integer id, @RequestBody @Valid @Min(value = 0,message = "i can't less than 0") Integer amount);

    @PutMapping("/user/{id}/decrease/balance")
    @ResponseStatus(HttpStatus.OK)
    public void decreaseBalanceHours(@PathVariable (name = "id") Integer id, @RequestBody @Valid @Min(value = 0,message = "i can't less than 0") Integer amount);

    @PutMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void editProfileUser(@PathVariable (name = "id") Integer id, @RequestBody @Valid UserRequestDto userRequestDto);

    @DeleteMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable (name = "id") Integer id);

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponseDto> findAllUsers();

}
