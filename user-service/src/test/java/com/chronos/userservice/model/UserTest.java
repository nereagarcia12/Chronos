package com.chronos.userservice.model;

import com.chronos.userservice.enums.Status;
import com.chronos.userservice.exceptions.InsufficientHoursException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;
    @BeforeEach
    void beforeEach() {
        user = new User("name","email","phone","city","password", LocalDate.now(),5,false, Status.ACTIVE);

    }

    @Test
    void increaseBalance() {
        user.increaseBalance(5);
        assertEquals(10,user.getBalanceHour());

    }

    @Test
    void decreaseBalance() {
        user.decreaseBalance(5);
        assertEquals(0,user.getBalanceHour());
    }

    @Test
    void decreaseBalance_Exception() {
        assertThrows(InsufficientHoursException.class, ()-> user.decreaseBalance(6));
    }

}
