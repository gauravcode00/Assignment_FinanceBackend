package com.FinanceDataProcessingAndAccessControlBackend.Assignment.dtos;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class DashboardSummaryDto {
    private BigDecimal totalIncome;
    private BigDecimal totalExpense;
    private BigDecimal netBalance;
    private List<FinancialRecordResponseDto> recentTransactions;
}