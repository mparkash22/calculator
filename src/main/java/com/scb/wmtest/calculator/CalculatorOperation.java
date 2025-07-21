package com.scb.wmtest.calculator;

/**
 * Enum representing calculator operations
 */
public enum CalculatorOperation {
    SUM("sum"),
    DIVIDE("divide"),
    MULTIPLY("multiply"),
    SUBTRACT("subtract");
    
    private final String value;
    
    CalculatorOperation(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    @Override
    public String toString() {
        return value;
    }
    
    /**
     * Get operation from string value
     * @param value the string value
     * @return the corresponding operation or null if not found
     */
    public static CalculatorOperation fromValue(String value) {
        for (CalculatorOperation operation : values()) {
            if (operation.value.equalsIgnoreCase(value)) {
                return operation;
            }
        }
        return null;
    }
} 