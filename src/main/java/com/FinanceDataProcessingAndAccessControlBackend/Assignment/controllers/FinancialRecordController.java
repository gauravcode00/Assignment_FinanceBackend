package com.FinanceDataProcessingAndAccessControlBackend.Assignment.controllers;

import com.FinanceDataProcessingAndAccessControlBackend.Assignment.dtos.FinancialRecordRequestDto;
import com.FinanceDataProcessingAndAccessControlBackend.Assignment.dtos.FinancialRecordResponseDto;
import com.FinanceDataProcessingAndAccessControlBackend.Assignment.models.User;
import com.FinanceDataProcessingAndAccessControlBackend.Assignment.services.FinancialRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/records")
@RequiredArgsConstructor
public class FinancialRecordController {

    private final FinancialRecordService recordService;

    @PostMapping
    public ResponseEntity<FinancialRecordResponseDto> createRecord(@RequestBody FinancialRecordRequestDto requestDto, @AuthenticationPrincipal User currentUser) {
        // AuthenticationPrincipal  annotation reads the SecurityContext and send me the current logined user


        FinancialRecordResponseDto createdRecord = recordService.createRecord(requestDto, currentUser);

        return new ResponseEntity<>(createdRecord, HttpStatus.CREATED);
    }
}