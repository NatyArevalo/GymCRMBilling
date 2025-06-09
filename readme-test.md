# Trainer Billing Service - Tests

## Overview
This document provides information about the test setup and structure for the Trainer Billing Service project. The tests are designed to ensure the functionality and reliability of the application's features.

## Test Frameworks and Tools
- **JUnit 5**: Used for unit and integration testing.
- **Cucumber**: Used for behavior-driven development (BDD) tests.
- **Spring Boot Test**: Provides utilities for testing Spring Boot applications.
- **Mockito**: Used for mocking dependencies in unit tests.

## Test Profiles
The tests use a dedicated Spring profile (`-Dspring.profiles.active=test`) to isolate the test environment from production. Configuration for the test profile is located in `src/test/resources/application-test.yml`.

## Test Structure
### Unit Tests
Unit tests focus on individual components and services. They are located in:
- `src/test/java/com/gymcrm/trainerbilling/Service`

### Component Tests
Tests that validate the individual behavior of a component. They are located in:
- `src/test/java/com/gymcrm/trainerbilling/StepDefinitions`

### BDD Tests
Behavior-driven tests are implemented using Cucumber. They are located in:
- `src/test/java/com/gymcrm/trainerbilling/StepDefinitions`

## How to Run Tests
1. Navigate to the project directory:
   ```bash
   cd trainerbilling