package com.FinanceDataProcessingAndAccessControlBackend.Assignment.dtos;

import com.FinanceDataProcessingAndAccessControlBackend.Assignment.enums.RecordType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class FinancialRecordResponseDto {
    private Long id;
    private BigDecimal amount;
    private RecordType type;
    private String category;
    private LocalDate date;
    private String notes;
    private Long createdById;
    private LocalDateTime createdAt;
}