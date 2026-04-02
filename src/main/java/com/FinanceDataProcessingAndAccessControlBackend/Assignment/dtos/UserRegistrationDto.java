package com.FinanceDataProcessingAndAccessControlBackend.Assignment.dtos;

import com.FinanceDataProcessingAndAccessControlBackend.Assignment.enums.Role;
import lombok.Data;

@Data
public class UserRegistrationDto {
    private String email;
    private String password;
    private Role role;
}