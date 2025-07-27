package com.scb.wmtest.calculator;

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
 * End-to-end tests for Calculator Application
 */
@SpringBootTest
@AutoConfigureWebMvc
@ActiveProfiles("test")
class CalculatorApplicationE2ETest {

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
    @DisplayName("Should perform all basic operations via GET requests")
    void testAllBasicOperationsViaGet() throws Exception {
        // Test addition
        mockMvc.perform(get("/api/v1/calculator/calculate")
                .param("action", "sum")
                .param("val1", "10")
                .param("val2", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.result").value(15));

        // Test subtraction
        mockMvc.perform(get("/api/v1/calculator/calculate")
                .param("action", "subtract")
                .param("val1", "10")
                .param("val2", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.result").value(5));

        // Test multiplication
        mockMvc.perform(get("/api/v1/calculator/calculate")
                .param("action", "multiply")
                .param("val1", "10")
                .param("val2", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.result").value(50));

        // Test division
        mockMvc.perform(get("/api/v1/calculator/calculate")
                .param("action", "divide")
                .param("val1", "10")
                .param("val2", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.result").value(2));
    }

    @Test
    @DisplayName("Should perform all basic operations via POST requests")
    void testAllBasicOperationsViaPost() throws Exception {
        // Test addition
        CalculateRequest addRequest = new CalculateRequest("sum", 10, 5);
        mockMvc.perform(post("/api/v1/calculator/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(addRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.result").value(15));

        // Test subtraction
        CalculateRequest subtractRequest = new CalculateRequest("subtract", 10, 5);
        mockMvc.perform(post("/api/v1/calculator/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(subtractRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.result").value(5));

        // Test multiplication
        CalculateRequest multiplyRequest = new CalculateRequest("multiply", 10, 5);
        mockMvc.perform(post("/api/v1/calculator/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(multiplyRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.result").value(50));

        // Test division
        CalculateRequest divideRequest = new CalculateRequest("divide", 10, 5);
        mockMvc.perform(post("/api/v1/calculator/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(divideRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.result").value(2));
    }

    @Test
    @DisplayName("Should handle edge cases correctly")
    void testEdgeCases() throws Exception {
        // Test with zero
        mockMvc.perform(get("/api/v1/calculator/calculate")
                .param("action", "sum")
                .param("val1", "0")
                .param("val2", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.result").value(5));

        // Test with negative numbers
        mockMvc.perform(get("/api/v1/calculator/calculate")
                .param("action", "sum")
                .param("val1", "-10")
                .param("val2", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.result").value(-5));

        // Test with large numbers
        mockMvc.perform(get("/api/v1/calculator/calculate")
                .param("action", "multiply")
                .param("val1", "1000")
                .param("val2", "1000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.result").value(1000000));
    }

    @Test
    @DisplayName("Should handle error cases correctly")
    void testErrorCases() throws Exception {
        // Test division by zero
        mockMvc.perform(get("/api/v1/calculator/calculate")
                .param("action", "divide")
                .param("val1", "10")
                .param("val2", "0"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value("DIVISION_BY_ZERO"));

        // Test invalid operation
        mockMvc.perform(get("/api/v1/calculator/calculate")
                .param("action", "invalid")
                .param("val1", "10")
                .param("val2", "5"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value("INVALID_OPERATION"));

        // Test missing parameters
        mockMvc.perform(get("/api/v1/calculator/calculate"))
                .andExpect(status().isBadRequest());

        // Test invalid parameter types
        mockMvc.perform(get("/api/v1/calculator/calculate")
                .param("action", "sum")
                .param("val1", "invalid")
                .param("val2", "5"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value("TYPE_MISMATCH"));
    }

    @Test
    @DisplayName("Should handle health check endpoint")
    void testHealthEndpoint() throws Exception {
        mockMvc.perform(get("/api/v1/calculator/health"))
                .andExpect(status().isOk())
                .andExpect(content().string("Calculator service is running"));
    }

    @Test
    @DisplayName("Should handle operations endpoint")
    void testOperationsEndpoint() throws Exception {
        mockMvc.perform(get("/api/v1/calculator/operations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value("sum"))
                .andExpect(jsonPath("$[1]").value("subtract"))
                .andExpect(jsonPath("$[2]").value("multiply"))
                .andExpect(jsonPath("$[3]").value("divide"));
    }

    @Test
    @DisplayName("Should handle case insensitive operations")
    void testCaseInsensitiveOperations() throws Exception {
        // Test uppercase operation
        mockMvc.perform(get("/api/v1/calculator/calculate")
                .param("action", "SUM")
                .param("val1", "10")
                .param("val2", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.result").value(15));

        // Test mixed case operation
        mockMvc.perform(get("/api/v1/calculator/calculate")
                .param("action", "Sum")
                .param("val1", "10")
                .param("val2", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.result").value(15));
    }

    @Test
    @DisplayName("Should validate request body correctly")
    void testRequestBodyValidation() throws Exception {
        // Test with invalid JSON
        mockMvc.perform(post("/api/v1/calculator/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content("invalid json"))
                .andExpect(status().isBadRequest());

        // Test with missing required fields
        CalculateRequest invalidRequest = new CalculateRequest();
        mockMvc.perform(post("/api/v1/calculator/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Should return proper response structure")
    void testResponseStructure() throws Exception {
        mockMvc.perform(get("/api/v1/calculator/calculate")
                .param("action", "sum")
                .param("val1", "10")
                .param("val2", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.apiVersion").value("1.0"))
                .andExpect(jsonPath("$.params.action").value("sum"))
                .andExpect(jsonPath("$.data.result").value(15))
                .andExpect(jsonPath("$.timestamp").exists());
    }
} 