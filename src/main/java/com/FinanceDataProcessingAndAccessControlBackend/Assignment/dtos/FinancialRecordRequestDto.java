package com.FinanceDataProcessingAndAccessControlBackend.Assignment.dtos;

import com.FinanceDataProcessingAndAccessControlBackend.Assignment.enums.RecordType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class FinancialRecordRequestDto {
    private BigDecimal amount;
    private RecordType type; // INCOME or EXPENSE
    private String category;
    private LocalDate date;
    private String notes;
}