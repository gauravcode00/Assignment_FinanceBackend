package com.FinanceDataProcessingAndAccessControlBackend.Assignment.repositories;

import com.FinanceDataProcessingAndAccessControlBackend.Assignment.models.FinancialRecord;
import com.FinanceDataProcessingAndAccessControlBackend.Assignment.enums.RecordType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FinancialRecordRepository extends JpaRepository<FinancialRecord, Long> {

    // Find all records belonging to a specific user
    List<FinancialRecord> findByCreatedById(Long userId);

    // Find all records of a specific type (e.g., all INCOME or all EXPENSE)
    List<FinancialRecord> findByType(RecordType type);

    // Find all records for a specific category
    List<FinancialRecord> findByCategory(String category);


    // Calculate total Income
    @org.springframework.data.jpa.repository.Query("SELECT COALESCE(SUM(f.amount), 0) FROM FinancialRecord f WHERE f.type = 'INCOME'")
    java.math.BigDecimal getTotalIncome();

    // Calculate total Expense
    @org.springframework.data.jpa.repository.Query("SELECT COALESCE(SUM(f.amount), 0) FROM FinancialRecord f WHERE f.type = 'EXPENSE'")
    java.math.BigDecimal getTotalExpense();

    // for filtering the data
    @org.springframework.data.jpa.repository.Query("SELECT f FROM FinancialRecord f WHERE " +
            "(:type IS NULL OR f.type = :type) AND " +
            "(:category IS NULL OR f.category = :category) AND " +
            "(:startDate IS NULL OR f.date >= :startDate) AND " +
            "(:endDate IS NULL OR f.date <= :endDate)")
    List<FinancialRecord> filterRecords(
            @org.springframework.data.repository.query.Param("type") RecordType type,
            @org.springframework.data.repository.query.Param("category") String category,
            @org.springframework.data.repository.query.Param("startDate") LocalDate startDate,
            @org.springframework.data.repository.query.Param("endDate") LocalDate endDate);

    // Get the 5 most recent transactions (Spring Data JPA automatically understands this method name!)
    List<FinancialRecord> findTop5ByOrderByDateDescCreatedAtDesc();
}