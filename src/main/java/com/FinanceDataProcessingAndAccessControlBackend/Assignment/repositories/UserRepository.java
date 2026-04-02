package com.FinanceDataProcessingAndAccessControlBackend.Assignment.repositories;

import com.FinanceDataProcessingAndAccessControlBackend.Assignment.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Custom method to find a user by their email for login/authentication
    Optional<User> findByEmail(String email);

    // Checks if an email already exists in the database
    boolean existsByEmail(String email);
}