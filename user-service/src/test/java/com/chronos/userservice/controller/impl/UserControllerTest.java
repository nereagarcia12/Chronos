package com.chronos.userservice.controller.impl;

import com.chronos.userservice.dto.UserRequestDto;
import com.chronos.userservice.dto.UserResponseDto;
import com.chronos.userservice.enums.ERole;
import com.chronos.userservice.enums.Status;
import com.chronos.userservice.model.Role;
import com.chronos.userservice.model.User;
import com.chronos.userservice.service.impl.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class UserControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private UserService userService;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private User user;
    private UserRequestDto userRequestDto;
    private UserResponseDto userResponseDto;
    private Role role;
    private Integer amount;
    private String email;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
        role = new Role(ERole.ROLE_USER);
        user = new User("name","email","phone","city","password", LocalDate.now(),5,false, Status.ACTIVE);
        user.setId(1);
        user.setRoles(Set.of(role));
        userRequestDto = new UserRequestDto("name","email","phone","city","password");
        userResponseDto = new UserResponseDto(1,"name","email","phone","city", LocalDate.now(),5,false);
        amount = 5;
        email = "email";
    }

    @Test
    void findByUserById() throws Exception {
        String expectedJson = "{\"id\":1,\"name\":\"name\",\"email\":\"email\",\"phone\":\"phone\",\"city\":\"city\",\"createdAt\":\"2021-03-13\",\"balanceHour\":5,\"pendingTransaction\":false}";
        when(userService.findById(1)).thenReturn(userResponseDto);

        MvcResult result = mockMvc
                .perform(get("/user/1"))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(expectedJson, result.getResponse().getContentAsString());
    }

    @Test
    void findByEmail() throws Exception {
        String expectedJson ="{\"id\":1,\"name\":\"name\",\"email\":\"email\",\"phone\":\"phone\",\"city\":\"city\",\"password\":\"password\",\"createdAt\":\"2021-03-13\",\"balanceHour\":5,\"pendingTransaction\":false,\"status\":\"ACTIVE\",\"roles\":[{\"id\":null,\"name\":\"ROLE_USER\"}]}";
        when(userService.findByEmail(email)).thenReturn(Optional.ofNullable(user));

        MvcResult result = mockMvc
                .perform(get("/user/email/email"))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(expectedJson, result.getResponse().getContentAsString());

    }

    @Test
    void createUser() throws Exception {
        String body = objectMapper.writeValueAsString(userRequestDto);

        MvcResult result = mockMvc
                .perform(post("/user")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        verify(userService).createUser(userRequestDto);

    }

    @Test
    void increaseBalanceHours() throws Exception {
        String body = objectMapper.writeValueAsString(amount);

        MvcResult result = mockMvc
                .perform(put("/user/1/increase/balance")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        verify(userService).increaseBalance(1,amount);
    }

    @Test
    void decreaseBalanceHours() throws Exception {
        String body = objectMapper.writeValueAsString(amount);

        MvcResult result = mockMvc
                .perform(put("/user/1/decrease/balance")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        verify(userService).decreaseBalance(1,amount);
    }

    @Test
    void editProfileUser() throws Exception {
        String body = objectMapper.writeValueAsString(userRequestDto);

        MvcResult result = mockMvc
                .perform(put("/user/1")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        verify(userService).editUser(userRequestDto,1);
    }

    @Test
    void deleteUser() throws Exception {
        MvcResult result = mockMvc
                .perform(delete("/user/1"))
                .andExpect(status().isOk())
                .andReturn();

        verify(userService).deleteUser(1);
    }
}
