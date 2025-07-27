# Enhanced Calculator Application

A robust Spring Boot calculator application with comprehensive error handling, validation, logging, and automated testing.

## Features

- **Mathematical Operations**: Addition, subtraction, multiplication, and division
- **RESTful API**: Both GET and POST endpoints for calculations
- **Input Validation**: Comprehensive validation for all inputs
- **Error Handling**: Custom exceptions with detailed error messages
- **Logging**: Structured logging for debugging and monitoring
- **API Documentation**: OpenAPI/Swagger documentation
- **Health Checks**: Application health monitoring
- **Comprehensive Testing**: Unit, integration, and end-to-end tests

## Technology Stack

- **Java 8**
- **Spring Boot 2.5.5**
- **Spring Web MVC**
- **Spring Validation**
- **Spring Actuator**
- **OpenAPI 3 (Swagger)**
- **JUnit 5**
- **Mockito**
- **Maven**

## Project Structure

```
calculator/
├── src/
│   ├── main/
│   │   ├── java/com/scb/wmtest/calculator/
│   │   │   ├── CalculatorApplication.java
│   │   │   ├── CalculatorOperation.java
│   │   │   ├── controller/
│   │   │   │   └── CalculateController.java
│   │   │   ├── services/
│   │   │   │   └── CalculateService.java
│   │   │   ├── model/
│   │   │   │   ├── CalculateRequest.java
│   │   │   │   ├── CalculateResponse.java
│   │   │   │   ├── Data.java
│   │   │   │   ├── Params.java
│   │   │   │   └── ErrorResponse.java
│   │   │   └── exception/
│   │   │       ├── CalculatorException.java
│   │   │       └── GlobalExceptionHandler.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       ├── java/com/scb/wmtest/calculator/
│       │   ├── CalculatorApplicationTests.java
│       │   ├── CalculatorApplicationE2ETest.java
│       │   ├── CalculatorOperationTest.java
│       │   ├── controller/
│       │   │   └── CalculateControllerIntegrationTest.java
│       │   ├── services/
│       │   │   └── CalculateServiceTest.java
│       │   ├── model/
│       │   │   ├── CalculateRequestTest.java
│       │   │   └── CalculateResponseTest.java
│       │   └── exception/
│       │       └── GlobalExceptionHandlerTest.java
│       └── resources/
│           └── application-test.properties
└── pom.xml
```

## API Endpoints

### 1. Calculate via GET
```
GET /api/v1/calculator/calculate?action={operation}&val1={number}&val2={number}
```

**Parameters:**
- `action` (required): Mathematical operation (sum, subtract, multiply, divide)
- `val1` (required): First operand (integer)
- `val2` (required): Second operand (integer)

**Example:**
```bash
curl "http://localhost:8080/api/v1/calculator/calculate?action=sum&val1=10&val2=5"
```

**Response:**
```json
{
  "apiVersion": "1.0",
  "params": {
    "action": "sum"
  },
  "data": {
    "result": 15
  },
  "timestamp": "2024-01-15 10:30:45"
}
```

### 2. Calculate via POST
```
POST /api/v1/calculator/calculate
Content-Type: application/json
```

**Request Body:**
```json
{
  "action": "sum",
  "val1": 10,
  "val2": 5
}
```

**Response:**
```json
{
  "apiVersion": "1.0",
  "params": {
    "action": "sum"
  },
  "data": {
    "result": 15
  },
  "timestamp": "2024-01-15 10:30:45"
}
```

### 3. Health Check
```
GET /api/v1/calculator/health
```

**Response:**
```
Calculator service is running
```

### 4. Get Supported Operations
```
GET /api/v1/calculator/operations
```

**Response:**
```json
["sum", "subtract", "multiply", "divide"]
```

## Error Handling

The application provides comprehensive error handling with standardized error responses:

### Error Response Format
```json
{
  "errorCode": "ERROR_CODE",
  "message": "Error description",
  "timestamp": "2024-01-15 10:30:45"
}
```

### Common Error Codes
- `DIVISION_BY_ZERO`: Division by zero attempted
- `INVALID_OPERATION`: Unsupported mathematical operation
- `INVALID_INPUT`: Invalid input parameters
- `TYPE_MISMATCH`: Invalid parameter type
- `VALIDATION_ERROR`: Input validation failed
- `INTERNAL_ERROR`: Unexpected server error

## Running the Application

### Prerequisites
- Java 8 or higher
- Maven 3.6 or higher

### Build and Run
```bash
# Clone the repository
git clone <repository-url>
cd calculator

# Build the application
mvn clean install

# Run the application
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### Access API Documentation
Once the application is running, you can access the Swagger UI at:
```
http://localhost:8080/swagger-ui.html
```

## Testing

### Run All Tests
```bash
mvn test
```

### Run Specific Test Categories
```bash
# Unit tests only
mvn test -Dtest=*Test

# Integration tests only
mvn test -Dtest=*IntegrationTest

# End-to-end tests only
mvn test -Dtest=*E2ETest
```

### Test Coverage
The application includes comprehensive test coverage:
- **Unit Tests**: Service layer, model classes, exception handlers
- **Integration Tests**: Controller layer with MockMvc
- **End-to-End Tests**: Full application testing
- **Exception Tests**: Error handling scenarios

## Key Improvements Made

### 1. Code Quality
- ✅ Proper naming conventions (renamed `enumaction` to `CalculatorOperation`)
- ✅ Comprehensive JavaDoc documentation
- ✅ Consistent code formatting
- ✅ Proper package structure

### 2. Error Handling
- ✅ Custom `CalculatorException` class
- ✅ Global exception handler with standardized responses
- ✅ Input validation with detailed error messages
- ✅ Division by zero protection
- ✅ Type mismatch handling

### 3. API Design
- ✅ RESTful API design
- ✅ OpenAPI/Swagger documentation
- ✅ Request/Response DTOs with validation
- ✅ Health check endpoints
- ✅ Proper HTTP status codes

### 4. Testing
- ✅ Unit tests for all components
- ✅ Integration tests for controllers
- ✅ End-to-end tests for complete workflows
- ✅ Exception handling tests
- ✅ Edge case testing

### 5. Logging and Monitoring
- ✅ Structured logging with SLF4J
- ✅ Debug and info level logging
- ✅ Error logging with context
- ✅ Spring Actuator for monitoring

### 6. Security and Validation
- ✅ Input validation with Bean Validation
- ✅ Parameter validation
- ✅ Request body validation
- ✅ Type safety

## Configuration

### Application Properties
```properties
# Server configuration
server.port=8080

# Logging configuration
logging.level.com.scb.wmtest.calculator=INFO
logging.level.org.springframework.web=INFO

# Actuator configuration
management.endpoints.web.exposure.include=health,info
management.endpoint.health.show-details=always
```

### Test Configuration
```properties
# Test-specific configuration
spring.profiles.active=test
logging.level.com.scb.wmtest.calculator=DEBUG
server.port=0
```

## Best Practices Implemented

1. **SOLID Principles**: Single responsibility, dependency injection
2. **DRY Principle**: No code duplication
3. **Fail Fast**: Early validation and error detection
4. **Defensive Programming**: Null checks and validation
5. **Comprehensive Testing**: High test coverage
6. **API Design**: RESTful principles and proper HTTP status codes
7. **Error Handling**: Graceful error handling with meaningful messages
8. **Logging**: Appropriate logging levels and context
9. **Documentation**: Comprehensive API documentation
10. **Configuration**: Environment-specific configurations

## Future Enhancements

- [ ] Add more mathematical operations (power, square root, etc.)
- [ ] Implement rate limiting
- [ ] Add authentication and authorization
- [ ] Implement caching for repeated calculations
- [ ] Add metrics and monitoring
- [ ] Implement database persistence for calculation history
- [ ] Add support for decimal numbers
- [ ] Implement async calculation for complex operations

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Ensure all tests pass
6. Submit a pull request

## License

This project is licensed under the MIT License. 