package com.chronos.userservice.controller.interfaces;

import com.chronos.userservice.dto.UserRequestDto;
import com.chronos.userservice.dto.UserResponseDto;

import javax.validation.constraints.Min;


public interface IUserController {
    public UserResponseDto findByUserById(Integer id);
    public void createUser( UserRequestDto userRequestDto);
    public void editProfileUser( Integer id, UserRequestDto userRequestDto);
    public void deleteUser( Integer id);
    public void increaseBalanceHours( Integer id, Integer amount);
    public void decreaseBalanceHours( Integer id, Integer amount);
}
