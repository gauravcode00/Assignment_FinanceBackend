package com.FinanceDataProcessingAndAccessControlBackend.Assignment.services;


import com.FinanceDataProcessingAndAccessControlBackend.Assignment.dtos.UserRegistrationDto;
import com.FinanceDataProcessingAndAccessControlBackend.Assignment.dtos.UserResponseDto;
import com.FinanceDataProcessingAndAccessControlBackend.Assignment.enums.UserStatus;
import com.FinanceDataProcessingAndAccessControlBackend.Assignment.exceptions.DuplicateResourceException;
import com.FinanceDataProcessingAndAccessControlBackend.Assignment.models.User;
import com.FinanceDataProcessingAndAccessControlBackend.Assignment.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto registerUser(UserRegistrationDto dto){

        if(userRepository.existsByEmail(dto.getEmail())){
            throw new DuplicateResourceException("Email is already in use: " + dto.getEmail());
        }

        User user = User.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .role(dto.getRole())
                .status(UserStatus.ACTIVE)
                .build();

        User savedUser = userRepository.save(user);

        return UserResponseDto.builder()
                .id(savedUser.getId())
                .email(savedUser.getEmail())
                .role(savedUser.getRole())
                .status(savedUser.getStatus())
                .createdAt(savedUser.getCreatedAt())
                .build();
    }

}
