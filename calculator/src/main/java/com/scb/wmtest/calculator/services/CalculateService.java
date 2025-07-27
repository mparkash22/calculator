package com.scb.wmtest.calculator.services;

import com.scb.wmtest.calculator.CalculatorOperation;
import com.scb.wmtest.calculator.exception.CalculatorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Service for performing calculator operations
 */
@Service
public class CalculateService {
    
    private static final Logger logger = LoggerFactory.getLogger(CalculateService.class);
    
    /**
     * Performs the specified mathematical operation on two numbers
     * 
     * @param action the mathematical operation to perform
     * @param val1 the first operand
     * @param val2 the second operand
     * @return the result of the calculation
     * @throws CalculatorException if the operation is invalid or division by zero occurs
     */
    public double performAction(String action, double val1, double val2) {
        logger.info("Performing calculation: {} {} {}", val1, action, val2);
        
        CalculatorOperation operation = CalculatorOperation.fromValue(action);
        if (operation == null) {
            logger.error("Invalid operation requested: {}", action);
            throw new CalculatorException("Invalid operation: " + action + ". Supported operations: sum, subtract, multiply, divide, power, percentage", "INVALID_OPERATION");
        }
        
        double result;
        try {
            switch (operation) {
                case SUM:
                    result = val1 + val2;
                    break;
                case SUBTRACT:
                    result = val1 - val2;
                    break;
                case MULTIPLY:
                    result = val1 * val2;
                    break;
                case DIVIDE:
                    if (val2 == 0) {
                        logger.error("Division by zero attempted: {} / {}", val1, val2);
                        throw new CalculatorException("Division by zero is not allowed", "DIVISION_BY_ZERO");
                    }
                    result = val1 / val2;
                    break;
                case POWER:
                    result = Math.pow(val1, val2);
                    break;
                case PERCENTAGE:
                    result = (val1 * val2) / 100.0;
                    break;
                default:
                    logger.error("Unsupported operation: {}", operation);
                    throw new CalculatorException("Unsupported operation: " + operation, "UNSUPPORTED_OPERATION");
            }
            
            logger.info("Calculation completed successfully: {} {} {} = {}", val1, action, val2, result);
            return result;
            
        } catch (CalculatorException e) {
            // Re-throw CalculatorException as-is
            throw e;
        } catch (ArithmeticException e) {
            logger.error("Arithmetic exception during calculation: {} {} {}", val1, action, val2, e.getMessage());
            throw new CalculatorException("Arithmetic error: " + e.getMessage(), "ARITHMETIC_ERROR");
        } catch (Exception e) {
            logger.error("Unexpected error during calculation: {} {} {}", val1, action, val2, e.getMessage());
            throw new CalculatorException("Unexpected error during calculation", "CALCULATION_ERROR");
        }
    }

    /**
     * Performs the specified mathematical operation on a single number
     * 
     * @param action the mathematical operation to perform
     * @param val the operand
     * @return the result of the calculation
     * @throws CalculatorException if the operation is invalid
     */
    public double performSingleAction(String action, double val) {
        logger.info("Performing single calculation: {} {}", action, val);
        
        CalculatorOperation operation = CalculatorOperation.fromValue(action);
        if (operation == null) {
            logger.error("Invalid operation requested: {}", action);
            throw new CalculatorException("Invalid operation: " + action, "INVALID_OPERATION");
        }
        
        double result;
        try {
            switch (operation) {
                case SQUARE_ROOT:
                    if (val < 0) {
                        throw new CalculatorException("Cannot calculate square root of negative number", "INVALID_INPUT");
                    }
                    result = Math.sqrt(val);
                    break;
                case SQUARE:
                    result = Math.pow(val, 2);
                    break;
                case CUBE:
                    result = Math.pow(val, 3);
                    break;
                case SIN:
                    result = Math.sin(Math.toRadians(val));
                    break;
                case COS:
                    result = Math.cos(Math.toRadians(val));
                    break;
                case TAN:
                    result = Math.tan(Math.toRadians(val));
                    break;
                case LOG:
                    if (val <= 0) {
                        throw new CalculatorException("Cannot calculate logarithm of non-positive number", "INVALID_INPUT");
                    }
                    result = Math.log10(val);
                    break;
                case LN:
                    if (val <= 0) {
                        throw new CalculatorException("Cannot calculate natural logarithm of non-positive number", "INVALID_INPUT");
                    }
                    result = Math.log(val);
                    break;
                case ABS:
                    result = Math.abs(val);
                    break;
                case FACTORIAL:
                    if (val < 0 || val != Math.floor(val)) {
                        throw new CalculatorException("Factorial is only defined for non-negative integers", "INVALID_INPUT");
                    }
                    result = calculateFactorial((int) val);
                    break;
                case RECIPROCAL:
                    if (val == 0) {
                        throw new CalculatorException("Cannot calculate reciprocal of zero", "DIVISION_BY_ZERO");
                    }
                    result = 1.0 / val;
                    break;
                default:
                    logger.error("Unsupported single operation: {}", operation);
                    throw new CalculatorException("Unsupported operation: " + operation, "UNSUPPORTED_OPERATION");
            }
            
            logger.info("Single calculation completed successfully: {} {} = {}", action, val, result);
            return result;
            
        } catch (CalculatorException e) {
            // Re-throw CalculatorException as-is
            throw e;
        } catch (ArithmeticException e) {
            logger.error("Arithmetic exception during calculation: {} {}", action, val, e.getMessage());
            throw new CalculatorException("Arithmetic error: " + e.getMessage(), "ARITHMETIC_ERROR");
        } catch (Exception e) {
            logger.error("Unexpected error during calculation: {} {}", action, val, e.getMessage());
            throw new CalculatorException("Unexpected error during calculation", "CALCULATION_ERROR");
        }
    }

    /**
     * Calculates factorial of a non-negative integer
     * 
     * @param n the number to calculate factorial for
     * @return the factorial result
     */
    private double calculateFactorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        double result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
    
    /**
     * Validates the input parameters for a calculation
     * 
     * @param action the mathematical operation
     * @param val1 the first operand
     * @param val2 the second operand
     * @throws CalculatorException if validation fails
     */
    public void validateInput(String action, int val1, int val2) {
        if (action == null || action.trim().isEmpty()) {
            throw new CalculatorException("Action cannot be null or empty", "INVALID_INPUT");
        }
        
        CalculatorOperation operation = CalculatorOperation.fromValue(action);
        if (operation == null) {
            throw new CalculatorException("Invalid operation: " + action, "INVALID_OPERATION");
        }
        
        // Additional validation for division
        if (operation == CalculatorOperation.DIVIDE && val2 == 0) {
            throw new CalculatorException("Division by zero is not allowed", "DIVISION_BY_ZERO");
        }
        
        logger.debug("Input validation passed for operation: {} with values: {}, {}", action, val1, val2);
    }
}
