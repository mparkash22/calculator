package com.scb.wmtest.calculator.exception;

import com.scb.wmtest.calculator.model.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for GlobalExceptionHandler
 */
class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        exceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    @DisplayName("Should handle CalculatorException correctly")
    void testHandleCalculatorException() {
        // Given
        CalculatorException exception = new CalculatorException("Test error", "TEST_ERROR");

        // When
        ResponseEntity<ErrorResponse> response = exceptionHandler.handleCalculatorException(exception);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("TEST_ERROR", response.getBody().getErrorCode());
        assertEquals("Test error", response.getBody().getMessage());
        assertNotNull(response.getBody().getTimestamp());
    }

    @Test
    @DisplayName("Should handle ArithmeticException correctly")
    void testHandleArithmeticException() {
        // Given
        ArithmeticException exception = new ArithmeticException("Division by zero");

        // When
        ResponseEntity<ErrorResponse> response = exceptionHandler.handleArithmeticException(exception);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("DIVISION_BY_ZERO", response.getBody().getErrorCode());
        assertEquals("Division by zero is not allowed", response.getBody().getMessage());
        assertNotNull(response.getBody().getTimestamp());
    }

    @Test
    @DisplayName("Should handle MethodArgumentTypeMismatchException correctly")
    void testHandleMethodArgumentTypeMismatchException() throws NoSuchMethodException {
        // Given
        Method method = String.class.getMethod("substring", int.class);
        MethodParameter methodParameter = new MethodParameter(method, 0);
        MethodArgumentTypeMismatchException exception = new MethodArgumentTypeMismatchException(
            "invalid", Integer.class, "val1", methodParameter, new RuntimeException("Type mismatch"));

        // When
        ResponseEntity<ErrorResponse> response = exceptionHandler.handleTypeMismatchException(exception);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("TYPE_MISMATCH", response.getBody().getErrorCode());
        assertTrue(response.getBody().getMessage().contains("val1"));
        assertTrue(response.getBody().getMessage().contains("Integer"));
        assertNotNull(response.getBody().getTimestamp());
    }

    @Test
    @DisplayName("Should handle generic Exception correctly")
    void testHandleGenericException() {
        // Given
        Exception exception = new Exception("Unexpected error");

        // When
        ResponseEntity<ErrorResponse> response = exceptionHandler.handleGenericException(exception);

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("INTERNAL_ERROR", response.getBody().getErrorCode());
        assertEquals("An unexpected error occurred. Please try again later.", response.getBody().getMessage());
        assertNotNull(response.getBody().getTimestamp());
    }

    @Test
    @DisplayName("Should handle CalculatorException with default error code")
    void testHandleCalculatorExceptionWithDefaultErrorCode() {
        // Given
        CalculatorException exception = new CalculatorException("Test error");

        // When
        ResponseEntity<ErrorResponse> response = exceptionHandler.handleCalculatorException(exception);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("CALC_ERROR", response.getBody().getErrorCode());
        assertEquals("Test error", response.getBody().getMessage());
    }

    @Test
    @DisplayName("Should handle CalculatorException with cause")
    void testHandleCalculatorExceptionWithCause() {
        // Given
        Exception cause = new RuntimeException("Root cause");
        CalculatorException exception = new CalculatorException("Test error", cause);

        // When
        ResponseEntity<ErrorResponse> response = exceptionHandler.handleCalculatorException(exception);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("CALC_ERROR", response.getBody().getErrorCode());
        assertEquals("Test error", response.getBody().getMessage());
    }
} 