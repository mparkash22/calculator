package com.scb.wmtest.calculator.controller;

import com.scb.wmtest.calculator.model.CalculateRequest;
import com.scb.wmtest.calculator.model.CalculateResponse;
import com.scb.wmtest.calculator.model.Data;
import com.scb.wmtest.calculator.model.Params;
import com.scb.wmtest.calculator.services.CalculateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * REST controller for calculator operations
 */
@RestController
@RequestMapping("/api/v1/calculator")
@Validated
@Tag(name = "Calculator", description = "Calculator API endpoints")
public class CalculateController {

    private static final Logger logger = LoggerFactory.getLogger(CalculateController.class);
    
    private final CalculateService calculateService;
    
    @Autowired
    public CalculateController(CalculateService calculateService) {
        this.calculateService = calculateService;
    }

    /**
     * Calculate using query parameters
     */
    @GetMapping("/calculate")
    @Operation(
        summary = "Perform calculation using query parameters",
        description = "Performs mathematical operations (sum, subtract, multiply, divide) on two integers"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Calculation successful",
            content = @Content(schema = @Schema(implementation = CalculateResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input or operation"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<CalculateResponse> calculate(
            @Parameter(description = "Mathematical operation", example = "sum", required = true)
            @RequestParam @NotBlank(message = "Action cannot be blank") String action,
            
            @Parameter(description = "First operand", example = "10", required = true)
            @RequestParam @NotNull(message = "First value cannot be null") Integer val1,
            
            @Parameter(description = "Second operand", example = "5", required = true)
            @RequestParam @NotNull(message = "Second value cannot be null") Integer val2) {
        
        logger.info("Received calculation request: action={}, val1={}, val2={}", action, val1, val2);
        
        // Validate input
        calculateService.validateInput(action, val1, val2);
        
        // Perform calculation
        int result = calculateService.performAction(action, val1, val2);
        
        CalculateResponse response = new CalculateResponse("1.0", new Params(action), new Data(result));
        logger.info("Calculation completed successfully: {} {} {} = {}", val1, action, val2, result);
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * Calculate using request body
     */
    @PostMapping("/calculate")
    @Operation(
        summary = "Perform calculation using request body",
        description = "Performs mathematical operations using JSON request body"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Calculation successful",
            content = @Content(schema = @Schema(implementation = CalculateResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input or operation"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<CalculateResponse> calculateWithBody(
            @Parameter(description = "Calculation request", required = true)
            @Valid @RequestBody CalculateRequest request) {
        
        logger.info("Received calculation request: {}", request);
        
        // Validate input
        calculateService.validateInput(request.getAction(), request.getVal1(), request.getVal2());
        
        // Perform calculation
        int result = calculateService.performAction(request.getAction(), request.getVal1(), request.getVal2());
        
        CalculateResponse response = new CalculateResponse("1.0", new Params(request.getAction()), new Data(result));
        logger.info("Calculation completed successfully: {} {} {} = {}", 
                   request.getVal1(), request.getAction(), request.getVal2(), result);
        
        return ResponseEntity.ok(response);
    }
    
    /**
     * Health check endpoint
     */
    @GetMapping("/health")
    @Operation(summary = "Health check", description = "Returns application health status")
    @ApiResponse(responseCode = "200", description = "Application is healthy")
    public ResponseEntity<String> health() {
        logger.debug("Health check requested");
        return ResponseEntity.ok("Calculator service is running");
    }
    
    /**
     * Get supported operations
     */
    @GetMapping("/operations")
    @Operation(summary = "Get supported operations", description = "Returns list of supported mathematical operations")
    @ApiResponse(responseCode = "200", description = "List of supported operations")
    public ResponseEntity<String[]> getSupportedOperations() {
        logger.debug("Supported operations requested");
        String[] operations = {"sum", "subtract", "multiply", "divide"};
        return ResponseEntity.ok(operations);
    }
}
