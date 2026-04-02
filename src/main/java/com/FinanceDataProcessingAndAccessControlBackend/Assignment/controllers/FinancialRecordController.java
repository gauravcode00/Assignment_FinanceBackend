package com.FinanceDataProcessingAndAccessControlBackend.Assignment.controllers;

import com.FinanceDataProcessingAndAccessControlBackend.Assignment.dtos.FinancialRecordRequestDto;
import com.FinanceDataProcessingAndAccessControlBackend.Assignment.dtos.FinancialRecordResponseDto;
import com.FinanceDataProcessingAndAccessControlBackend.Assignment.enums.RecordType;
import com.FinanceDataProcessingAndAccessControlBackend.Assignment.models.User;
import com.FinanceDataProcessingAndAccessControlBackend.Assignment.services.FinancialRecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/records")
@RequiredArgsConstructor
public class FinancialRecordController {

    private final FinancialRecordService recordService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<FinancialRecordResponseDto> createRecord(@Valid @RequestBody FinancialRecordRequestDto requestDto, @AuthenticationPrincipal User currentUser) {
        // AuthenticationPrincipal  annotation reads the SecurityContext and send me the current logined user


        FinancialRecordResponseDto createdRecord = recordService.createRecord(requestDto, currentUser);

        return new ResponseEntity<>(createdRecord, HttpStatus.CREATED);
    }

    // Updated READ with Filtering
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'ANALYST')")
    public ResponseEntity<List<FinancialRecordResponseDto>> getRecords(
            @RequestParam(required = false) RecordType type,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate) {

        return ResponseEntity.ok(recordService.getFilteredRecords(type, category, startDate, endDate));
    }

    // UPDATE (Strictly ADMIN only)
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FinancialRecordResponseDto> updateRecord(
            @PathVariable Long id,
            @Valid @RequestBody FinancialRecordRequestDto requestDto) {

        return ResponseEntity.ok(recordService.updateRecord(id, requestDto));
    }

    // DELETE (Strictly ADMIN only)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteRecord(@PathVariable Long id) {
        recordService.deleteRecord(id);
        return ResponseEntity.ok("Record deleted successfully.");
    }
}