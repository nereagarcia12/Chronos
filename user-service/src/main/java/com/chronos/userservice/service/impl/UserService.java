package com.chronos.userservice.service.impl;

import com.chronos.userservice.dto.UserRequestDto;
import com.chronos.userservice.dto.UserResponseDto;
import com.chronos.userservice.exceptions.NoPresentUser;
import com.chronos.userservice.model.User;
import com.chronos.userservice.repository.UserRepository;
import com.chronos.userservice.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponseDto findById(Integer id){
       return userRepository.findById(id).orElseThrow(NoPresentUser::new).toConvertDto();
    }


    public void createUser(UserRequestDto userRequestDto){
        User user = new User(userRequestDto.getName(), userRequestDto.getEmail(), userRequestDto.getPhone(), userRequestDto.getCity(), userRequestDto.getPassword());
        userRepository.save(user);
    }

    public void editUser(UserRequestDto userRequestDto, Integer id){
        User user = userRepository.findById(id).orElseThrow(NoPresentUser::new);
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setPhone(userRequestDto.getPhone());
        user.setCity(userRequestDto.getCity());
        user.setPassword(userRequestDto.getPassword());
        userRepository.save(user);
    }

    public void deleteUser(Integer id){
        User user = userRepository.findById(id).orElseThrow(NoPresentUser::new);
        userRepository.delete(user);
    }

    public void increaseBalance(Integer amount, Integer id){
        User user = userRepository.findById(id).orElseThrow(NoPresentUser::new);
        user.increaseBalance(amount);
        userRepository.save(user);
    }

    public void decreaseBalance(Integer amount, Integer id){
        User user = userRepository.findById(id).orElseThrow(NoPresentUser::new);
        user.decreaseBalance(amount);
        userRepository.save(user);
    }





}
