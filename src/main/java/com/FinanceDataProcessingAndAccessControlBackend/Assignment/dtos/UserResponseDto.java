package com.FinanceDataProcessingAndAccessControlBackend.Assignment.dtos;

import com.FinanceDataProcessingAndAccessControlBackend.Assignment.enums.Role;
import com.FinanceDataProcessingAndAccessControlBackend.Assignment.enums.UserStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserResponseDto {
    private Long id;
    private String email;
    private Role role;
    private UserStatus status;
    private LocalDateTime createdAt;
}