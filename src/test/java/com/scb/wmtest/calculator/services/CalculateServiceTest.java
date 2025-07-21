package com.scb.wmtest.calculator.services;

import com.scb.wmtest.calculator.CalculatorOperation;
import com.scb.wmtest.calculator.exception.CalculatorException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for CalculateService
 */
@ExtendWith(MockitoExtension.class)
class CalculateServiceTest {

    @InjectMocks
    private CalculateService calculateService;

    @BeforeEach
    void setUp() {
        // Setup if needed
    }

    @Test
    @DisplayName("Should perform addition correctly")
    void testAddition() {
        // Given
        String action = "sum";
        int val1 = 10;
        int val2 = 5;

        // When
        int result = calculateService.performAction(action, val1, val2);

        // Then
        assertEquals(15, result);
    }

    @Test
    @DisplayName("Should perform subtraction correctly")
    void testSubtraction() {
        // Given
        String action = "subtract";
        int val1 = 10;
        int val2 = 5;

        // When
        int result = calculateService.performAction(action, val1, val2);

        // Then
        assertEquals(5, result);
    }

    @Test
    @DisplayName("Should perform multiplication correctly")
    void testMultiplication() {
        // Given
        String action = "multiply";
        int val1 = 10;
        int val2 = 5;

        // When
        int result = calculateService.performAction(action, val1, val2);

        // Then
        assertEquals(50, result);
    }

    @Test
    @DisplayName("Should perform division correctly")
    void testDivision() {
        // Given
        String action = "divide";
        int val1 = 10;
        int val2 = 5;

        // When
        int result = calculateService.performAction(action, val1, val2);

        // Then
        assertEquals(2, result);
    }

    @Test
    @DisplayName("Should handle negative numbers correctly")
    void testNegativeNumbers() {
        // Given
        String action = "sum";
        int val1 = -10;
        int val2 = 5;

        // When
        int result = calculateService.performAction(action, val1, val2);

        // Then
        assertEquals(-5, result);
    }

    @Test
    @DisplayName("Should handle zero correctly")
    void testZeroValues() {
        // Given
        String action = "sum";
        int val1 = 0;
        int val2 = 5;

        // When
        int result = calculateService.performAction(action, val1, val2);

        // Then
        assertEquals(5, result);
    }

    @Test
    @DisplayName("Should throw exception for division by zero")
    void testDivisionByZero() {
        // Given
        String action = "divide";
        int val1 = 10;
        int val2 = 0;

        // When & Then
        CalculatorException exception = assertThrows(CalculatorException.class, () -> {
            calculateService.performAction(action, val1, val2);
        });

        assertEquals("Division by zero is not allowed", exception.getMessage());
        assertEquals("DIVISION_BY_ZERO", exception.getErrorCode());
    }

    @Test
    @DisplayName("Should throw exception for invalid operation")
    void testInvalidOperation() {
        // Given
        String action = "invalid";
        int val1 = 10;
        int val2 = 5;

        // When & Then
        CalculatorException exception = assertThrows(CalculatorException.class, () -> {
            calculateService.performAction(action, val1, val2);
        });

        assertTrue(exception.getMessage().contains("Invalid operation"));
        assertEquals("INVALID_OPERATION", exception.getErrorCode());
    }

    @Test
    @DisplayName("Should throw exception for null operation")
    void testNullOperation() {
        // Given
        String action = null;
        int val1 = 10;
        int val2 = 5;

        // When & Then
        CalculatorException exception = assertThrows(CalculatorException.class, () -> {
            calculateService.performAction(action, val1, val2);
        });

        assertTrue(exception.getMessage().contains("Invalid operation"));
    }

    @Test
    @DisplayName("Should handle case insensitive operations")
    void testCaseInsensitiveOperations() {
        // Given
        String action = "SUM";
        int val1 = 10;
        int val2 = 5;

        // When
        int result = calculateService.performAction(action, val1, val2);

        // Then
        assertEquals(15, result);
    }

    @Test
    @DisplayName("Should validate input correctly")
    void testValidateInput() {
        // Given
        String action = "sum";
        int val1 = 10;
        int val2 = 5;

        // When & Then
        assertDoesNotThrow(() -> {
            calculateService.validateInput(action, val1, val2);
        });
    }

    @Test
    @DisplayName("Should throw exception for invalid input validation")
    void testValidateInputWithInvalidOperation() {
        // Given
        String action = "invalid";
        int val1 = 10;
        int val2 = 5;

        // When & Then
        CalculatorException exception = assertThrows(CalculatorException.class, () -> {
            calculateService.validateInput(action, val1, val2);
        });

        assertEquals("INVALID_OPERATION", exception.getErrorCode());
    }

    @Test
    @DisplayName("Should throw exception for null action in validation")
    void testValidateInputWithNullAction() {
        // Given
        String action = null;
        int val1 = 10;
        int val2 = 5;

        // When & Then
        CalculatorException exception = assertThrows(CalculatorException.class, () -> {
            calculateService.validateInput(action, val1, val2);
        });

        assertEquals("INVALID_INPUT", exception.getErrorCode());
    }

    @Test
    @DisplayName("Should throw exception for empty action in validation")
    void testValidateInputWithEmptyAction() {
        // Given
        String action = "";
        int val1 = 10;
        int val2 = 5;

        // When & Then
        CalculatorException exception = assertThrows(CalculatorException.class, () -> {
            calculateService.validateInput(action, val1, val2);
        });

        assertEquals("INVALID_INPUT", exception.getErrorCode());
    }

    @Test
    @DisplayName("Should throw exception for division by zero in validation")
    void testValidateInputWithDivisionByZero() {
        // Given
        String action = "divide";
        int val1 = 10;
        int val2 = 0;

        // When & Then
        CalculatorException exception = assertThrows(CalculatorException.class, () -> {
            calculateService.validateInput(action, val1, val2);
        });

        assertEquals("DIVISION_BY_ZERO", exception.getErrorCode());
    }
} 