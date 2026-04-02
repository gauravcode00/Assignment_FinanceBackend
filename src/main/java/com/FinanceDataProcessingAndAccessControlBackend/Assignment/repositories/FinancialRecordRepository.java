package com.FinanceDataProcessingAndAccessControlBackend.Assignment.repositories;

import com.FinanceDataProcessingAndAccessControlBackend.Assignment.models.FinancialRecord;
import com.FinanceDataProcessingAndAccessControlBackend.Assignment.enums.RecordType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinancialRecordRepository extends JpaRepository<FinancialRecord, Long> {

    // Find all records belonging to a specific user
    List<FinancialRecord> findByCreatedById(Long userId);

    // Find all records of a specific type (e.g., all INCOME or all EXPENSE)
    List<FinancialRecord> findByType(RecordType type);

    // Find all records for a specific category
    List<FinancialRecord> findByCategory(String category);
}