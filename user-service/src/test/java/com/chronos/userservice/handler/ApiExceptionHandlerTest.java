package com.chronos.userservice.handler;

import com.chronos.userservice.enums.ERole;
import com.chronos.userservice.enums.Status;
import com.chronos.userservice.exceptions.EmailAlreadyRegisteredException;
import com.chronos.userservice.exceptions.InsufficientHoursException;
import com.chronos.userservice.exceptions.UserNotFoundException;
import com.chronos.userservice.model.Role;
import com.chronos.userservice.model.User;
import com.chronos.userservice.service.impl.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ApiExceptionHandlerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private UserService userService;

    private MockMvc mockMvc;

    private User user;
    private Role role;



    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
        role = new Role(ERole.ROLE_USER);
        user = new User("name","email","phone","city","password", LocalDate.now(),5,false, Status.ACTIVE);
        user.setId(1);
        user.setRoles(Set.of(role));
    }

    @Test
    void noPresentUser() throws Exception {
        String expectedJson = "{\"error\":\"El usuario no existe \"}";
        UserNotFoundException exception = new UserNotFoundException();
        doThrow(exception).when(userService).findById(1);

        MvcResult result = mockMvc
                .perform(get("/user/1"))
                .andExpect(status().isNotFound())
                .andReturn();

        assertEquals(expectedJson, result.getResponse().getContentAsString());

    }

    @Test
    void insufficientHoursException() throws Exception {
        String expectedJson = "{\"error\":\"No tienes horas suficientes para esta solicitud\"}";
        InsufficientHoursException exception = new InsufficientHoursException();
        doThrow(exception).when(userService).findById(1);

        MvcResult result = mockMvc
                .perform(get("/user/1"))
                .andExpect(status().isBadRequest())
                .andReturn();

        assertEquals(expectedJson, result.getResponse().getContentAsString());

    }

    @Test
    void emailAlreadyRegistered() throws Exception {
        String expectedJson = "{\"error\":\"Este email ya esta registrado. \"}";
        EmailAlreadyRegisteredException exception = new EmailAlreadyRegisteredException();
        doThrow(exception).when(userService).findById(1);

        MvcResult result = mockMvc
                .perform(get("/user/1"))
                .andExpect(status().isPreconditionFailed())
                .andReturn();

        assertEquals(expectedJson, result.getResponse().getContentAsString());

    }
}
