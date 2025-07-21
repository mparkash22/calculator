package com.scb.wmtest.calculator.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for CalculateRequest model
 */
class CalculateRequestTest {

    @Test
    @DisplayName("Should create CalculateRequest with all parameters")
    void testCalculateRequestWithParameters() {
        // Given
        String action = "sum";
        Integer val1 = 10;
        Integer val2 = 5;

        // When
        CalculateRequest request = new CalculateRequest(action, val1, val2);

        // Then
        assertEquals(action, request.getAction());
        assertEquals(val1, request.getVal1());
        assertEquals(val2, request.getVal2());
    }

    @Test
    @DisplayName("Should create CalculateRequest with default constructor")
    void testCalculateRequestDefaultConstructor() {
        // When
        CalculateRequest request = new CalculateRequest();

        // Then
        assertNull(request.getAction());
        assertNull(request.getVal1());
        assertNull(request.getVal2());
    }

    @Test
    @DisplayName("Should set and get action correctly")
    void testSetAndGetAction() {
        // Given
        CalculateRequest request = new CalculateRequest();
        String action = "multiply";

        // When
        request.setAction(action);

        // Then
        assertEquals(action, request.getAction());
    }

    @Test
    @DisplayName("Should set and get val1 correctly")
    void testSetAndGetVal1() {
        // Given
        CalculateRequest request = new CalculateRequest();
        Integer val1 = 20;

        // When
        request.setVal1(val1);

        // Then
        assertEquals(val1, request.getVal1());
    }

    @Test
    @DisplayName("Should set and get val2 correctly")
    void testSetAndGetVal2() {
        // Given
        CalculateRequest request = new CalculateRequest();
        Integer val2 = 30;

        // When
        request.setVal2(val2);

        // Then
        assertEquals(val2, request.getVal2());
    }

    @Test
    @DisplayName("Should handle null values correctly")
    void testHandleNullValues() {
        // Given
        CalculateRequest request = new CalculateRequest();

        // When
        request.setAction(null);
        request.setVal1(null);
        request.setVal2(null);

        // Then
        assertNull(request.getAction());
        assertNull(request.getVal1());
        assertNull(request.getVal2());
    }

    @Test
    @DisplayName("Should handle negative values correctly")
    void testHandleNegativeValues() {
        // Given
        CalculateRequest request = new CalculateRequest();
        Integer val1 = -10;
        Integer val2 = -5;

        // When
        request.setVal1(val1);
        request.setVal2(val2);

        // Then
        assertEquals(val1, request.getVal1());
        assertEquals(val2, request.getVal2());
    }

    @Test
    @DisplayName("Should handle zero values correctly")
    void testHandleZeroValues() {
        // Given
        CalculateRequest request = new CalculateRequest();
        Integer val1 = 0;
        Integer val2 = 0;

        // When
        request.setVal1(val1);
        request.setVal2(val2);

        // Then
        assertEquals(val1, request.getVal1());
        assertEquals(val2, request.getVal2());
    }
} 