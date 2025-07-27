package com.scb.wmtest.calculator.model;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Data model for calculation results
 */
@Schema(description = "Calculation result data")
public class Data {
    
    @Schema(description = "Calculation result", example = "15.0")
    private double result;
    
    public Data() {
    }
    
    public Data(double result) {
        this.result = result;
    }
    
    public double getResult() {
        return result;
    }
    
    public void setResult(double result) {
        this.result = result;
    }
}
