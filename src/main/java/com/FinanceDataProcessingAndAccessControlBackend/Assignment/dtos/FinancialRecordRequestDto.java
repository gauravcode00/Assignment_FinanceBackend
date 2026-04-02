package com.FinanceDataProcessingAndAccessControlBackend.Assignment.dtos;

import com.FinanceDataProcessingAndAccessControlBackend.Assignment.enums.RecordType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class FinancialRecordRequestDto {

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be strictly positive")
    private BigDecimal amount;

    @NotNull(message = "Record type (INCOME/EXPENSE) is required")
    private RecordType type;

    @NotBlank(message = "Category cannot be blank")
    private String category;

    @NotNull(message = "Date is required")
    @PastOrPresent(message = "Date cannot be in the future")
    private LocalDate date;

    // Notes are optional, so no validation annotations needed here
    private String notes;
}