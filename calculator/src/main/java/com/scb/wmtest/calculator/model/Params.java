package com.scb.wmtest.calculator.model;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * Parameters for calculator operations
 */
@Schema(description = "Calculator operation parameters")
public class Params {
    
    @Schema(description = "Mathematical operation to perform", example = "sum")
    @NotBlank(message = "Action cannot be blank")
    @Pattern(regexp = "^(sum|subtract|multiply|divide)$", message = "Action must be one of: sum, subtract, multiply, divide")
    private String action;
    
    public Params() {
    }
    
    public Params(String action) {
        this.action = action;
    }
    
    public String getAction() {
        return action;
    }
    
    public void setAction(String action) {
        this.action = action;
    }
}
