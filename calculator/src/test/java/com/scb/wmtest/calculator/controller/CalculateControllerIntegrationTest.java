package com.scb.wmtest.calculator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scb.wmtest.calculator.model.CalculateRequest;
import com.scb.wmtest.calculator.model.CalculateResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for CalculateController
 */
@SpringBootTest
@AutoConfigureWebMvc
@ActiveProfiles("test")
class CalculateControllerIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @DisplayName("Should perform addition via GET request")
    void testAdditionViaGet() throws Exception {
        // Given
        String action = "sum";
        int val1 = 10;
        int val2 = 5;

        // When & Then
        mockMvc.perform(get("/api/v1/calculator/calculate")
                .param("action", action)
                .param("val1", String.valueOf(val1))
                .param("val2", String.valueOf(val2)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.apiVersion").value("1.0"))
                .andExpect(jsonPath("$.params.action").value(action))
                .andExpect(jsonPath("$.data.result").value(15));
    }

    @Test
    @DisplayName("Should perform subtraction via GET request")
    void testSubtractionViaGet() throws Exception {
        // Given
        String action = "subtract";
        int val1 = 10;
        int val2 = 5;

        // When & Then
        mockMvc.perform(get("/api/v1/calculator/calculate")
                .param("action", action)
                .param("val1", String.valueOf(val1))
                .param("val2", String.valueOf(val2)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.result").value(5));
    }

    @Test
    @DisplayName("Should perform multiplication via GET request")
    void testMultiplicationViaGet() throws Exception {
        // Given
        String action = "multiply";
        int val1 = 10;
        int val2 = 5;

        // When & Then
        mockMvc.perform(get("/api/v1/calculator/calculate")
                .param("action", action)
                .param("val1", String.valueOf(val1))
                .param("val2", String.valueOf(val2)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.result").value(50));
    }

    @Test
    @DisplayName("Should perform division via GET request")
    void testDivisionViaGet() throws Exception {
        // Given
        String action = "divide";
        int val1 = 10;
        int val2 = 5;

        // When & Then
        mockMvc.perform(get("/api/v1/calculator/calculate")
                .param("action", action)
                .param("val1", String.valueOf(val1))
                .param("val2", String.valueOf(val2)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.result").value(2));
    }

    @Test
    @DisplayName("Should perform addition via POST request")
    void testAdditionViaPost() throws Exception {
        // Given
        CalculateRequest request = new CalculateRequest("sum", 10, 5);

        // When & Then
        mockMvc.perform(post("/api/v1/calculator/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.result").value(15));
    }

    @Test
    @DisplayName("Should perform subtraction via POST request")
    void testSubtractionViaPost() throws Exception {
        // Given
        CalculateRequest request = new CalculateRequest("subtract", 10, 5);

        // When & Then
        mockMvc.perform(post("/api/v1/calculator/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.result").value(5));
    }

    @Test
    @DisplayName("Should return error for division by zero via GET")
    void testDivisionByZeroViaGet() throws Exception {
        // Given
        String action = "divide";
        int val1 = 10;
        int val2 = 0;

        // When & Then
        mockMvc.perform(get("/api/v1/calculator/calculate")
                .param("action", action)
                .param("val1", String.valueOf(val1))
                .param("val2", String.valueOf(val2)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value("DIVISION_BY_ZERO"))
                .andExpect(jsonPath("$.message").value("Division by zero is not allowed"));
    }

    @Test
    @DisplayName("Should return error for division by zero via POST")
    void testDivisionByZeroViaPost() throws Exception {
        // Given
        CalculateRequest request = new CalculateRequest("divide", 10, 0);

        // When & Then
        mockMvc.perform(post("/api/v1/calculator/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value("DIVISION_BY_ZERO"));
    }

    @Test
    @DisplayName("Should return error for invalid operation via GET")
    void testInvalidOperationViaGet() throws Exception {
        // Given
        String action = "invalid";
        int val1 = 10;
        int val2 = 5;

        // When & Then
        mockMvc.perform(get("/api/v1/calculator/calculate")
                .param("action", action)
                .param("val1", String.valueOf(val1))
                .param("val2", String.valueOf(val2)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value("INVALID_OPERATION"));
    }

    @Test
    @DisplayName("Should return error for invalid operation via POST")
    void testInvalidOperationViaPost() throws Exception {
        // Given
        CalculateRequest request = new CalculateRequest("invalid", 10, 5);

        // When & Then
        mockMvc.perform(post("/api/v1/calculator/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value("INVALID_OPERATION"));
    }

    @Test
    @DisplayName("Should return error for missing parameters via GET")
    void testMissingParametersViaGet() throws Exception {
        // When & Then
        mockMvc.perform(get("/api/v1/calculator/calculate"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Should return error for invalid parameter types via GET")
    void testInvalidParameterTypesViaGet() throws Exception {
        // When & Then
        mockMvc.perform(get("/api/v1/calculator/calculate")
                .param("action", "sum")
                .param("val1", "invalid")
                .param("val2", "5"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value("TYPE_MISMATCH"));
    }

    @Test
    @DisplayName("Should return health status")
    void testHealthEndpoint() throws Exception {
        // When & Then
        mockMvc.perform(get("/api/v1/calculator/health"))
                .andExpect(status().isOk())
                .andExpect(content().string("Calculator service is running"));
    }

    @Test
    @DisplayName("Should return supported operations")
    void testSupportedOperationsEndpoint() throws Exception {
        // When & Then
        mockMvc.perform(get("/api/v1/calculator/operations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value("sum"))
                .andExpect(jsonPath("$[1]").value("subtract"))
                .andExpect(jsonPath("$[2]").value("multiply"))
                .andExpect(jsonPath("$[3]").value("divide"));
    }

    @Test
    @DisplayName("Should handle negative numbers correctly")
    void testNegativeNumbers() throws Exception {
        // Given
        String action = "sum";
        int val1 = -10;
        int val2 = 5;

        // When & Then
        mockMvc.perform(get("/api/v1/calculator/calculate")
                .param("action", action)
                .param("val1", String.valueOf(val1))
                .param("val2", String.valueOf(val2)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.result").value(-5));
    }

    @Test
    @DisplayName("Should handle zero values correctly")
    void testZeroValues() throws Exception {
        // Given
        String action = "sum";
        int val1 = 0;
        int val2 = 5;

        // When & Then
        mockMvc.perform(get("/api/v1/calculator/calculate")
                .param("action", action)
                .param("val1", String.valueOf(val1))
                .param("val2", String.valueOf(val2)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.result").value(5));
    }
} 