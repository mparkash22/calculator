package com.scb.wmtest.calculator.exception;

/**
 * Custom exception for calculator operations
 */
public class CalculatorException extends RuntimeException {
    
    private final String errorCode;
    
    public CalculatorException(String message) {
        super(message);
        this.errorCode = "CALC_ERROR";
    }
    
    public CalculatorException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
    
    public CalculatorException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = "CALC_ERROR";
    }
    
    public String getErrorCode() {
        return errorCode;
    }
} 