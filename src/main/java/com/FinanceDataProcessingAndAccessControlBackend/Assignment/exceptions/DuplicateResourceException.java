package com.FinanceDataProcessingAndAccessControlBackend.Assignment.exceptions;

public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String message) {
        super(message);
    }
}