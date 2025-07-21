package com.scb.wmtest.calculator.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

/**
 * Response model for calculator operations
 */
@Schema(description = "Calculator operation response")
public class CalculateResponse {
    
    @Schema(description = "API version", example = "1.0")
    private String apiVersion;
    
    @Schema(description = "Operation parameters")
    private Params params;
    
    @Schema(description = "Calculation result data")
    private Data data;
    
    @Schema(description = "Response timestamp")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
    
    public CalculateResponse() {
        this.timestamp = LocalDateTime.now();
    }
    
    public CalculateResponse(String apiVersion, Params params, Data data) {
        this.apiVersion = apiVersion;
        this.params = params;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }
    
    public String getApiVersion() {
        return apiVersion;
    }
    
    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }
    
    public Params getParams() {
        return params;
    }
    
    public void setParams(Params params) {
        this.params = params;
    }
    
    public Data getData() {
        return data;
    }
    
    public void setData(Data data) {
        this.data = data;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
