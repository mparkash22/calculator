package com.scb.wmtest.calculator.model;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Request model for calculator operations
 */
@Schema(description = "Calculator operation request")
public class CalculateRequest {
    
    @Schema(description = "Mathematical operation to perform", example = "sum", required = true)
    @NotNull(message = "Action cannot be null")
    @Pattern(regexp = "^(sum|subtract|multiply|divide|power|percentage)$", message = "Action must be one of: sum, subtract, multiply, divide, power, percentage")
    private String action;
    
    @Schema(description = "First operand", example = "10.0", required = true)
    @NotNull(message = "First value cannot be null")
    private Double val1;
    
    @Schema(description = "Second operand", example = "5.0", required = true)
    @NotNull(message = "Second value cannot be null")
    private Double val2;
    
    public CalculateRequest() {
    }
    
    public CalculateRequest(String action, Double val1, Double val2) {
        this.action = action;
        this.val1 = val1;
        this.val2 = val2;
    }
    
    public String getAction() {
        return action;
    }
    
    public void setAction(String action) {
        this.action = action;
    }
    
    public Double getVal1() {
        return val1;
    }
    
    public void setVal1(Double val1) {
        this.val1 = val1;
    }
    
    public Double getVal2() {
        return val2;
    }
    
    public void setVal2(Double val2) {
        this.val2 = val2;
    }
} 