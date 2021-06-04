package com.chronos.userservice.service.interfaces;

import com.chronos.userservice.dto.UserRequestDto;
import com.chronos.userservice.dto.UserResponseDto;

import java.util.List;


public interface IUserService {

    public UserResponseDto findById(Integer id);
    public void createUser(UserRequestDto userRequestDto);
    public void editUser(UserRequestDto userRequestDto, Integer id);
    public void deleteUser(Integer id);
    public void increaseBalance(Integer id, Integer amount);
    public void decreaseBalance(Integer id, Integer amount);
    public List<UserResponseDto> findAllUsers();
}
