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
    @Pattern(regexp = "^(sum|subtract|multiply|divide)$", message = "Action must be one of: sum, subtract, multiply, divide")
    private String action;
    
    @Schema(description = "First operand", example = "10", required = true)
    @NotNull(message = "First value cannot be null")
    private Integer val1;
    
    @Schema(description = "Second operand", example = "5", required = true)
    @NotNull(message = "Second value cannot be null")
    private Integer val2;
    
    public CalculateRequest() {
    }
    
    public CalculateRequest(String action, Integer val1, Integer val2) {
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
    
    public Integer getVal1() {
        return val1;
    }
    
    public void setVal1(Integer val1) {
        this.val1 = val1;
    }
    
    public Integer getVal2() {
        return val2;
    }
    
    public void setVal2(Integer val2) {
        this.val2 = val2;
    }
} 