package com.chronos.userservice.service.impl;

import com.chronos.userservice.dto.UserRequestDto;
import com.chronos.userservice.dto.UserResponseDto;
import com.chronos.userservice.enums.ERole;
import com.chronos.userservice.enums.Status;
import com.chronos.userservice.exceptions.NoPresentUser;
import com.chronos.userservice.model.Role;
import com.chronos.userservice.model.User;
import com.chronos.userservice.repository.RoleRepository;
import com.chronos.userservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.util.Optional;

import java.time.LocalDate;
import java.util.Set;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;

    private User user;
    private UserRequestDto userRequestDto;
    private UserResponseDto userResponseDto;
    private Role role;
    private String email;


    @BeforeEach
    void setUp() { role = new Role(ERole.ROLE_USER);
       user = new User("name","email","phone","city","password", LocalDate.now(),5,false, Status.ACTIVE);
       user.setId(1);
       user.setRoles(Set.of(role));
       userRequestDto = new UserRequestDto("name","email","phone","city","password");
       userResponseDto = new UserResponseDto(1,"name","email","phone","city", LocalDate.now(),5,false);
       email = "email";
    }


    @Test
    void findById() {
        when(userRepository.findById(1)).thenReturn(Optional.ofNullable(user));

        UserResponseDto userResponseDtoResponse = userService.findById(1);

        assertEquals(userResponseDto,userResponseDtoResponse);

    }

    @Test
    void findById_Exception() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());

       assertThrows(NoPresentUser.class, () -> userService.findById(1));

    }

    @Test
    void findByEmail(){
        when(userRepository.findByEmail(email)).thenReturn(Optional.ofNullable(user));

        Optional<User> userResponse = userService.findByEmail(email);

        assertEquals(Optional.of(user), userResponse);
    }

    @Test
    void createUser() {
        user.setId(null);
        when(roleRepository.findByName(ERole.ROLE_USER)).thenReturn(Optional.ofNullable(role));

        userService.createUser(userRequestDto);

        verify(userRepository).save(user);
    }

    @Test
    void editUser() {
        when(userRepository.findById(1)).thenReturn(Optional.ofNullable(user));
        user.setName("hola");
        user.setEmail("hola");
        user.setPhone("hola");
        user.setCity("hola");

        userService.editUser(userRequestDto,1);

        verify(userRepository).save(user);
    }

    @Test
    void deleteUser() {
        when(userRepository.findById(1)).thenReturn(Optional.ofNullable(user));

        userService.deleteUser(1);

        verify(userRepository).delete(user);
    }

    @Test
    void increaseBalance() {
        when(userRepository.findById(1)).thenReturn(Optional.ofNullable(user));

        userService.increaseBalance(1,5);

        verify(userRepository).save(user);
    }

    @Test
    void decreaseBalance() {
        when(userRepository.findById(1)).thenReturn(Optional.ofNullable(user));

        userService.decreaseBalance(1,5);

        verify(userRepository).save(user);
    }
}
