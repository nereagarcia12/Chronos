package com.chronos.userservice.service.impl;

import com.chronos.userservice.dto.UserRequestDto;
import com.chronos.userservice.dto.UserResponseDto;
import com.chronos.userservice.enums.ERole;
import com.chronos.userservice.exceptions.NoPresentUser;
import com.chronos.userservice.model.Role;
import com.chronos.userservice.model.User;
import com.chronos.userservice.repository.RoleRepository;
import com.chronos.userservice.repository.UserRepository;
import com.chronos.userservice.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public UserResponseDto findById(Integer id){
       return getUser(id).toConvertDto();
    }

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public void createUser(UserRequestDto userRequestDto){
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);

        User user = new User(userRequestDto.getName(), userRequestDto.getEmail(), userRequestDto.getPhone(), userRequestDto.getCity(), userRequestDto.getPassword(), roles);
        userRepository.save(user);
    }

    public void editUser(UserRequestDto userRequestDto, Integer id){
        User user = getUser(id);
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setPhone(userRequestDto.getPhone());
        user.setCity(userRequestDto.getCity());
        userRepository.save(user);
    }

    public void deleteUser(Integer id){
        User user = getUser(id);
        userRepository.delete(user);
    }

    public void increaseBalance(Integer id, Integer amount){
        User user = getUser(id);
        user.increaseBalance(amount);
        userRepository.save(user);
    }

    public void decreaseBalance(Integer id, Integer amount){
        User user = getUser(id);
        user.decreaseBalance(amount);
        userRepository.save(user);
    }

    private User getUser(Integer id) {
        return userRepository.findById(id).orElseThrow(NoPresentUser::new);
    }
}
