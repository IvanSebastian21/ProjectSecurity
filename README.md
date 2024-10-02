# Spring Security Application

This project demonstrates the implementation of **Spring Security** with **Java 17** and **Spring Boot**. It includes configurations for securing endpoints with roles and authorities, and provides examples of user authentication and authorization using an in-memory user details service.

## Features

- **Authentication and Authorization**: Protects endpoints based on user roles (`ADMIN`, `MANAGER`) and authorities (`READ`).
- **Stateless Session Management**: Configured for stateless session management using `SessionCreationPolicy.STATELESS`.
- **CSRF Disabled**: CSRF protection is disabled for certain endpoints.
- **In-Memory User Details Service**: Users and their roles/authorities are defined in memory for simplicity.
  
## Prerequisites

- Java 17
- Maven 3.x
- Spring Boot 2.7.x

## Endpoints

| Method | Endpoint          | Role Required    | Authority Required | Description                      |
|--------|-------------------|------------------|--------------------|----------------------------------|
| GET    | `/auth/public`     | None             | None               | Public endpoint, accessible to all users. |
| GET    | `/auth/manager`    | `MANAGER`        | None               | Endpoint accessible to `MANAGER` role. |
| GET    | `/auth/user`       | `ADMIN`, `MANAGER` | `READ`             | Secured endpoint, requires `READ` authority. |

## Security Configuration

- **Authorization Configuration**: 
    - `/auth/public` is open to everyone.
    - `/auth/user` is restricted to users with the `READ` authority.
    - `/auth/manager` is restricted to users with `MANAGER` role.
- **CSRF**: Disabled for `/public` endpoints.
- **Session**: Configured as stateless.

## Authentication Details

The application uses an in-memory authentication provider with the following users:

| Username | Password | Role      | Authority |
|----------|----------|-----------|-----------|
| `userAdmin`    | `1234` | `ADMIN`   | `READ`    |
| `userManager` | `5678` | `MANAGER` | `READ`    |

## Getting Started

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/spring-security-app.git

2. Navigate to the project directory:
   ```bash
    cd spring-security-app

3. Build the project with Maven:
   ```bash
    mvn clean install
4. Run the application:
   ```bash
   mvn spring-boot:run
