package com.FinanceDataProcessingAndAccessControlBackend.Assignment.services;

import com.FinanceDataProcessingAndAccessControlBackend.Assignment.dtos.DashboardSummaryDto;
import com.FinanceDataProcessingAndAccessControlBackend.Assignment.dtos.FinancialRecordResponseDto;
import com.FinanceDataProcessingAndAccessControlBackend.Assignment.models.FinancialRecord;
import com.FinanceDataProcessingAndAccessControlBackend.Assignment.repositories.FinancialRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final FinancialRecordRepository recordRepository;

    public DashboardSummaryDto getDashboardSummary() {
        BigDecimal totalIncome = recordRepository.getTotalIncome();
        BigDecimal totalExpense = recordRepository.getTotalExpense();

        // Net Balance = Income - Expense
        BigDecimal netBalance = totalIncome.subtract(totalExpense);

        // Fetch recent 5 records and map them to DTOs
        List<FinancialRecordResponseDto> recentTransactions = recordRepository.findTop5ByOrderByDateDescCreatedAtDesc()
                .stream()
                .map(this::mapToResponseDto)
                .toList();

        return DashboardSummaryDto.builder()
                .totalIncome(totalIncome)
                .totalExpense(totalExpense)
                .netBalance(netBalance)
                .recentTransactions(recentTransactions)
                .build();
    }

    // Quick helper mapping method
    private FinancialRecordResponseDto mapToResponseDto(FinancialRecord record) {
        return FinancialRecordResponseDto.builder()
                .id(record.getId())
                .amount(record.getAmount())
                .type(record.getType())
                .category(record.getCategory())
                .date(record.getDate())
                .notes(record.getNotes())
                .createdById(record.getCreatedBy().getId())
                .createdAt(record.getCreatedAt())
                .build();
    }
}