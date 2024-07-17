# Concert Booking API REST v1

## Introduction
This project is a **REST API** developed with Spring Boot for managing concert reservations. It implements Spring Security best practices for authentication and authorization.

### Technologies Used
#### Database
* DDL: MySql
* DML: DatabaseInitializer.java

#### Application
* SpringBoot
* SpringSecurity
* Jason Web Token
* Spring Data JPA
* Lombok

#### Testing
* Swagger: openapi-starter-webmvc-ui

## Setup and Configuration

### Prerequisites
- Java JDK 21 or higher
- Maven
- MySQL

### Database Configuration
* Modify the `application.properties` file with the database credentials

### Running the Application
1. Clone the repository
2. Navigate to the project directory
3. Run `mvn spring-boot:run`

### Project Structure

The project is organized into the following main packages:

* `com.project.utils`: Utilities for database population, JWT token generation, session identification
* `com.project.service`: Services implementing business logic
* `com.project.response`: Response types to be returned from controllers
* `com.project.repository`: Data access interfaces
* `com.project.model`: Entities, DTOs, enumerations
* `com.project.filters`: Filters handling login and authenticated calls
* `com.project.exception`: Exception definitions for individual entities + exception responses
* `com.project.controller`: REST controllers
* `com.project.config`: Spring Security configurations, WebConfig, ...

### Key Features
#### Authentication

Implemented in `AuthenticationService`:
Uses JWT for generating access and refresh tokens

#### Concert Management
`ConcertService` provides functionalities to view available concerts and manage seats

#### Reservations
`ReservationService` manages creation and modification of reservations

#### Ticket Management
`TicketService` handles updating the quantity of available tickets

### Security
Implemented using Spring Security
JWT-based authentication
CustomUserDetailsService for user details loading

### Accessing the API Documentation

You can view and interact with the API documentation in several ways:

1. **Swagger UI**: When the application is running, visit `http://localhost:8080/swagger-ui.html` to see the interactive API documentation.

2. **Raw OpenAPI Specification**: The full OpenAPI specification is available in JSON format at `docs/api-docs.json` in this repository.

## Error Handling

The project uses a centralized error handling system. Each error returns a response with:

- Error code
- Descriptive message
- Appropriate HTTP Status

### Key Error Codes:
- UA: Unauthorized
- EXT: Expired Token
- BR: Bad Request
- CNF: Concert Not Found
- PNF: Reservation Not Found

For a complete list of error codes, refer to the `ErrorCode.java` class.

## Security

### Authentication
- JWT (JSON Web Token) based
- Access and refresh tokens

### Authorization
- User roles: USER, ADMIN
- Endpoints protected using Spring security annotations

### Token Management
- Token generation: `JwtUtils.java`
- Token validation: `AuthorizationFilter.java`

### Best Practices
- Password hashing with BCrypt
- HTTPS in production (to be configured)
- Protection against CSRF and XSS

