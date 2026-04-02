package com.FinanceDataProcessingAndAccessControlBackend.Assignment.dtos;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;
    private String password;
}