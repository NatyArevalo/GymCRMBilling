# Trainer Billing Service

## Overview
Trainer Billing Service is a Spring Boot application designed to manage billing and authentication for trainers. It includes features such as JWT-based authentication, CORS configuration, and request/response logging.

## Features
- **JWT Authentication**: Secures endpoints using JSON Web Tokens.
- **CORS Configuration**: Allows cross-origin requests from specific origins.
- **Request/Response Logging**: Logs incoming requests and outgoing responses with unique transaction IDs.
- **Stateless Session Management**: Ensures the application does not maintain session state.

## Technologies Used
- **Java**: Programming language.
- **Spring Boot**: Framework for building the application.
- **Maven**: Dependency management and build tool.

## Configuration
### `application.properties`
- `spring.application.name`: Sets the application name.
- `jwt.secret`: Secret key for signing JWTs (configured via environment variable `JWT_SECRET`).

### Security
- Public endpoints:
    - `/trainerbilling/test`
    - `/trainerbilling/{username}`
    - `/trainerbilling/billing`
- All other endpoints require authentication.

### CORS
- Allowed origins:
    - `http://localhost:61100`
    - `http://127.0.0.1:8080`
- Allowed methods: `GET`, `POST`, `PUT`, `DELETE`, `OPTIONS`, `PATCH`.

## How to Run
1. Run Dockerized Application
2. If accessing directly to the application, make sure to send a JWT token in the header of the request.
3. To get a valid token you can use the /login endpoint with the following body:
```json
{
  "username": "INSERT VALID USERNAME",
  "password": "INSERT VALID PASSWORD"
}
```