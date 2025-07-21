package com.scb.wmtest.calculator.model;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Data model for calculation results
 */
@Schema(description = "Calculation result data")
public class Data {
    
    @Schema(description = "Calculation result", example = "15")
    private int result;
    
    public Data() {
    }
    
    public Data(int result) {
        this.result = result;
    }
    
    public int getResult() {
        return result;
    }
    
    public void setResult(int result) {
        this.result = result;
    }
}
