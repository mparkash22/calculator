package com.scb.wmtest.calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for CalculatorOperation enum
 */
class CalculatorOperationTest {

    @Test
    @DisplayName("Should return correct value for SUM")
    void testSumValue() {
        assertEquals("sum", CalculatorOperation.SUM.getValue());
    }

    @Test
    @DisplayName("Should return correct value for SUBTRACT")
    void testSubtractValue() {
        assertEquals("subtract", CalculatorOperation.SUBTRACT.getValue());
    }

    @Test
    @DisplayName("Should return correct value for MULTIPLY")
    void testMultiplyValue() {
        assertEquals("multiply", CalculatorOperation.MULTIPLY.getValue());
    }

    @Test
    @DisplayName("Should return correct value for DIVIDE")
    void testDivideValue() {
        assertEquals("divide", CalculatorOperation.DIVIDE.getValue());
    }

    @Test
    @DisplayName("Should return correct string representation for SUM")
    void testSumToString() {
        assertEquals("sum", CalculatorOperation.SUM.toString());
    }

    @Test
    @DisplayName("Should return correct string representation for SUBTRACT")
    void testSubtractToString() {
        assertEquals("subtract", CalculatorOperation.SUBTRACT.toString());
    }

    @Test
    @DisplayName("Should return correct string representation for MULTIPLY")
    void testMultiplyToString() {
        assertEquals("multiply", CalculatorOperation.MULTIPLY.toString());
    }

    @Test
    @DisplayName("Should return correct string representation for DIVIDE")
    void testDivideToString() {
        assertEquals("divide", CalculatorOperation.DIVIDE.toString());
    }

    @Test
    @DisplayName("Should find operation from lowercase value")
    void testFromValueLowerCase() {
        assertEquals(CalculatorOperation.SUM, CalculatorOperation.fromValue("sum"));
        assertEquals(CalculatorOperation.SUBTRACT, CalculatorOperation.fromValue("subtract"));
        assertEquals(CalculatorOperation.MULTIPLY, CalculatorOperation.fromValue("multiply"));
        assertEquals(CalculatorOperation.DIVIDE, CalculatorOperation.fromValue("divide"));
    }

    @Test
    @DisplayName("Should find operation from uppercase value")
    void testFromValueUpperCase() {
        assertEquals(CalculatorOperation.SUM, CalculatorOperation.fromValue("SUM"));
        assertEquals(CalculatorOperation.SUBTRACT, CalculatorOperation.fromValue("SUBTRACT"));
        assertEquals(CalculatorOperation.MULTIPLY, CalculatorOperation.fromValue("MULTIPLY"));
        assertEquals(CalculatorOperation.DIVIDE, CalculatorOperation.fromValue("DIVIDE"));
    }

    @Test
    @DisplayName("Should find operation from mixed case value")
    void testFromValueMixedCase() {
        assertEquals(CalculatorOperation.SUM, CalculatorOperation.fromValue("Sum"));
        assertEquals(CalculatorOperation.SUBTRACT, CalculatorOperation.fromValue("Subtract"));
        assertEquals(CalculatorOperation.MULTIPLY, CalculatorOperation.fromValue("Multiply"));
        assertEquals(CalculatorOperation.DIVIDE, CalculatorOperation.fromValue("Divide"));
    }

    @Test
    @DisplayName("Should return null for invalid value")
    void testFromValueInvalid() {
        assertNull(CalculatorOperation.fromValue("invalid"));
        assertNull(CalculatorOperation.fromValue(""));
        assertNull(CalculatorOperation.fromValue(null));
    }

    @Test
    @DisplayName("Should return all enum values")
    void testValues() {
        CalculatorOperation[] values = CalculatorOperation.values();
        assertEquals(4, values.length);
        
        assertTrue(contains(values, CalculatorOperation.SUM));
        assertTrue(contains(values, CalculatorOperation.SUBTRACT));
        assertTrue(contains(values, CalculatorOperation.MULTIPLY));
        assertTrue(contains(values, CalculatorOperation.DIVIDE));
    }

    private boolean contains(CalculatorOperation[] values, CalculatorOperation operation) {
        for (CalculatorOperation value : values) {
            if (value == operation) {
                return true;
            }
        }
        return false;
    }
} 