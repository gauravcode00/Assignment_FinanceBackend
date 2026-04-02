package com.FinanceDataProcessingAndAccessControlBackend.Assignment.services;


import com.FinanceDataProcessingAndAccessControlBackend.Assignment.dtos.AuthResponseDto;
import com.FinanceDataProcessingAndAccessControlBackend.Assignment.dtos.LoginRequestDto;
import com.FinanceDataProcessingAndAccessControlBackend.Assignment.dtos.UserRegistrationDto;
import com.FinanceDataProcessingAndAccessControlBackend.Assignment.dtos.UserResponseDto;
import com.FinanceDataProcessingAndAccessControlBackend.Assignment.enums.UserStatus;
import com.FinanceDataProcessingAndAccessControlBackend.Assignment.exceptions.DuplicateResourceException;
import com.FinanceDataProcessingAndAccessControlBackend.Assignment.models.User;
import com.FinanceDataProcessingAndAccessControlBackend.Assignment.repositories.UserRepository;
import com.FinanceDataProcessingAndAccessControlBackend.Assignment.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserResponseDto registerUser(UserRegistrationDto dto){

        if(userRepository.existsByEmail(dto.getEmail())){
            throw new DuplicateResourceException("Email is already in use: " + dto.getEmail());
        }

        User user = User.builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
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

    public AuthResponseDto loginUser(LoginRequestDto dto) {
        // 1. Spring Security checks if the email and password match. If not, it throws an error.
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
        );

        // 2. If we get here, credentials are correct. Fetch the user.
        User user = userRepository.findByEmail(dto.getEmail()).orElseThrow();

        // 3. Generate the token
        String jwtToken = jwtService.generateToken(user);

        // 4. Return the token
        return AuthResponseDto.builder()
                .token(jwtToken)
                .build();
    }

}
