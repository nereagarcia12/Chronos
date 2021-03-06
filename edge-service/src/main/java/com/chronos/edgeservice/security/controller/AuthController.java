package com.chronos.edgeservice.security.controller;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.chronos.edgeservice.apiresponse.transaction.TransactionResponseDto;
import com.chronos.edgeservice.client.TransactionClient;
import com.chronos.edgeservice.client.UserClient;
import com.chronos.edgeservice.security.JwtUtils;
import com.chronos.edgeservice.security.UserRequestDto;
import com.chronos.edgeservice.security.dto.JwtResponse;
import com.chronos.edgeservice.security.dto.LoginRequest;
import com.chronos.edgeservice.security.dto.UserResponseDto;
import com.chronos.edgeservice.security.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserClient userClient;

    @Autowired
    private TransactionClient transactionClient;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        List<TransactionResponseDto> transactions = transactionClient.getAllTransactionByUserId(Math.toIntExact(userDetails.getId())).stream().filter(
                transaction -> transaction.getStatus().equals("PENDING") && transaction.getReceiverUserId() == Math.toIntExact(userDetails.getId()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles,
                userDetails.getBalance(), userDetails.getName(), !transactions.isEmpty()));
    }


    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@Valid @RequestBody UserRequestDto signUpRequest) {

        signUpRequest.setPassword(encoder.encode(signUpRequest.getPassword()));
        userClient.createUser(signUpRequest);
    }
}
