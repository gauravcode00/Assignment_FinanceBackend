package com.FinanceDataProcessingAndAccessControlBackend.Assignment.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AuthResponseDto {
    private String token;
}