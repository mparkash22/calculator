package com.scb.wmtest.calculator.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for CalculateResponse model
 */
class CalculateResponseTest {

    @Test
    @DisplayName("Should create CalculateResponse with all parameters")
    void testCalculateResponseWithParameters() {
        // Given
        String apiVersion = "1.0";
        Params params = new Params("sum");
        Data data = new Data(15);

        // When
        CalculateResponse response = new CalculateResponse(apiVersion, params, data);

        // Then
        assertEquals(apiVersion, response.getApiVersion());
        assertEquals(params, response.getParams());
        assertEquals(data, response.getData());
        assertNotNull(response.getTimestamp());
    }

    @Test
    @DisplayName("Should create CalculateResponse with default constructor")
    void testCalculateResponseDefaultConstructor() {
        // When
        CalculateResponse response = new CalculateResponse();

        // Then
        assertNull(response.getApiVersion());
        assertNull(response.getParams());
        assertNull(response.getData());
        assertNotNull(response.getTimestamp());
    }

    @Test
    @DisplayName("Should set and get apiVersion correctly")
    void testSetAndGetApiVersion() {
        // Given
        CalculateResponse response = new CalculateResponse();
        String apiVersion = "2.0";

        // When
        response.setApiVersion(apiVersion);

        // Then
        assertEquals(apiVersion, response.getApiVersion());
    }

    @Test
    @DisplayName("Should set and get params correctly")
    void testSetAndGetParams() {
        // Given
        CalculateResponse response = new CalculateResponse();
        Params params = new Params("multiply");

        // When
        response.setParams(params);

        // Then
        assertEquals(params, response.getParams());
    }

    @Test
    @DisplayName("Should set and get data correctly")
    void testSetAndGetData() {
        // Given
        CalculateResponse response = new CalculateResponse();
        Data data = new Data(50);

        // When
        response.setData(data);

        // Then
        assertEquals(data, response.getData());
    }

    @Test
    @DisplayName("Should set and get timestamp correctly")
    void testSetAndGetTimestamp() {
        // Given
        CalculateResponse response = new CalculateResponse();
        LocalDateTime timestamp = LocalDateTime.now();

        // When
        response.setTimestamp(timestamp);

        // Then
        assertEquals(timestamp, response.getTimestamp());
    }

    @Test
    @DisplayName("Should have timestamp set automatically in constructor")
    void testTimestampSetAutomatically() {
        // Given
        LocalDateTime beforeCreation = LocalDateTime.now();

        // When
        CalculateResponse response = new CalculateResponse();

        // Then
        assertNotNull(response.getTimestamp());
        assertTrue(response.getTimestamp().isAfter(beforeCreation) || 
                  response.getTimestamp().equals(beforeCreation));
    }
} 