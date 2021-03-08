package com.chronos.edgeservice.client;

import com.chronos.edgeservice.security.UserRequestDto;
import com.chronos.edgeservice.security.dto.User;
import com.chronos.edgeservice.security.dto.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
}
