package com.FinanceDataProcessingAndAccessControlBackend.Assignment.controllers;


import com.FinanceDataProcessingAndAccessControlBackend.Assignment.dtos.AuthResponseDto;
import com.FinanceDataProcessingAndAccessControlBackend.Assignment.dtos.LoginRequestDto;
import com.FinanceDataProcessingAndAccessControlBackend.Assignment.dtos.UserRegistrationDto;
import com.FinanceDataProcessingAndAccessControlBackend.Assignment.dtos.UserResponseDto;
import com.FinanceDataProcessingAndAccessControlBackend.Assignment.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> registerUser(@RequestBody UserRegistrationDto registrationDto){

        UserResponseDto createdUser = userService.registerUser(registrationDto); // calling userService

        return  new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> loginUser(@RequestBody LoginRequestDto loginDto) {
        AuthResponseDto authResponse = userService.loginUser(loginDto);
        return ResponseEntity.ok(authResponse);
    }

}
