package com.FinanceDataProcessingAndAccessControlBackend.Assignment.services;

import com.FinanceDataProcessingAndAccessControlBackend.Assignment.dtos.FinancialRecordRequestDto;
import com.FinanceDataProcessingAndAccessControlBackend.Assignment.dtos.FinancialRecordResponseDto;
import com.FinanceDataProcessingAndAccessControlBackend.Assignment.models.FinancialRecord;
import com.FinanceDataProcessingAndAccessControlBackend.Assignment.models.User;
import com.FinanceDataProcessingAndAccessControlBackend.Assignment.repositories.FinancialRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FinancialRecordService {

    private final FinancialRecordRepository recordRepository;

    // record creation
    public FinancialRecordResponseDto createRecord(FinancialRecordRequestDto requestDto, User currentUser) {

        // Maping DTO to Entity and attach the currently logged-in user
        FinancialRecord record = FinancialRecord.builder()
                .amount(requestDto.getAmount())
                .type(requestDto.getType())
                .category(requestDto.getCategory())
                .date(requestDto.getDate())
                .notes(requestDto.getNotes())
                .createdBy(currentUser)
                .build();

        // record saving
        FinancialRecord savedRecord = recordRepository.save(record);

        // Convert saved entity back to a safe Response DTO
        return mapToResponseDto(savedRecord);
    }

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