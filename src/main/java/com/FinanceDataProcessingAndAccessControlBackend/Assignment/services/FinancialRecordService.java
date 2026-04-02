package com.FinanceDataProcessingAndAccessControlBackend.Assignment.services;

import com.FinanceDataProcessingAndAccessControlBackend.Assignment.dtos.FinancialRecordRequestDto;
import com.FinanceDataProcessingAndAccessControlBackend.Assignment.dtos.FinancialRecordResponseDto;
import com.FinanceDataProcessingAndAccessControlBackend.Assignment.enums.RecordType;
import com.FinanceDataProcessingAndAccessControlBackend.Assignment.models.FinancialRecord;
import com.FinanceDataProcessingAndAccessControlBackend.Assignment.models.User;
import com.FinanceDataProcessingAndAccessControlBackend.Assignment.repositories.FinancialRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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

    // 2. READ ALL RECORDS
    public java.util.List<FinancialRecordResponseDto> getAllRecords() {
        return recordRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .toList(); // Note: toList() is available in Java 16+
    }

    // 3. DELETE RECORD
    public void deleteRecord(Long id) {
        if (!recordRepository.existsById(id)) {
            throw new RuntimeException("Record not found with ID: " + id);
        }
        recordRepository.deleteById(id);
    }

    // 4. UPDATE RECORD
    public FinancialRecordResponseDto updateRecord(Long id, FinancialRecordRequestDto requestDto) {
        // Find the existing record
        FinancialRecord existingRecord = recordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found with ID: " + id));

        // Update the fields
        existingRecord.setAmount(requestDto.getAmount());
        existingRecord.setType(requestDto.getType());
        existingRecord.setCategory(requestDto.getCategory());
        existingRecord.setDate(requestDto.getDate());
        existingRecord.setNotes(requestDto.getNotes());

        // Save and return
        FinancialRecord updatedRecord = recordRepository.save(existingRecord);
        return mapToResponseDto(updatedRecord);
    }

    // 5. filterinng records
    public List<FinancialRecordResponseDto> getFilteredRecords(RecordType type, String category, LocalDate startDate, LocalDate endDate) {
        return recordRepository.filterRecords(type, category, startDate, endDate)
                .stream()
                .map(this::mapToResponseDto)
                .toList();
    }
}