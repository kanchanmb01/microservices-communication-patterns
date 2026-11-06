
## Prototype Objective

This prototype demonstrates service-to-servic communication in a Spring Boot microservices architecture using RestTemplate. The project showcases Eureka Service Discovery, API Gateway routing, Client-Side Load Balancing, and Resilience4j Circuit Breaker implementation for fault tolerance.

### Components

- API Gateway
- Eureka Discovery Server
- User Service
- Contacts Service
- MySQL Database

---

## Technology Stack
### Backend
- Java 17
- Spring Boot 3.5.16
- Spring Data JPA
- Hibernate

### Microservices Components
- Netflix Eureka Server
- Netflix Eureka Client
- Spring Cloud Gateway
- Spring Cloud LoadBalancer
- Resilience4j Circuit Breaker
- RestTemplate


### Database
- MySQL

### Build Tool
- Maven

### Testing
- Browser

---

### Request Flow

```text
Browser
    ↓
API-GATEWAY (8083)
    ↓
USER-MS (8081)
    ↓
RestTemplate
    ↓
CONTACTS-MS
```
### Load Balancing Flow
```text
Browser
    ↓
API-GATEWAY (8083)
    ↓
USER-MS (8081)
    ↓
RestTemplate
    ↓
Spring Cloud LoadBalancer
    ↓
Eureka Discovery Server
    ↓
CONTACTS-MS Instance
(8080 OR 8084 OR 8086)
```

### Features Implemented

- Service Discovery using Eureka
- API Routing using Spring Cloud Gateway
- Inter-Service Communication using RestTemplate
- Client Side Load Balancing
- Circuit Breaker using Resilience4j
- Multiple Contacts Service Instances

---

## Eureka Configuration

Eureka Server acts as a centralized service registry.All microservices register themselves with Eureka during startup.
It enables dynamic service discovery among microservices.

### Registered Services

- API-GATEWAY
- USER-MS
- CONTACTS-MS

### API Gateway

Spring Cloud Gateway acts as the single entry point for all external requests.

Configured routes:

- /api/user/** → USER-MS
- /api/contacts/** → CONTACTS-MS

Service names are resolved dynamically through Eureka Service Discovery.

---
## Load Balancer Configuration

Spring Cloud LoadBalancer is used with RestTemplate to distribute requests across available service instances.

### RestTemplate Configuration

```java
@Bean
@LoadBalanced
public RestTemplate getRestTemplate() {
    return new RestTemplate();
}
```

### Contacts Service Instances
- CONTACTS-MS : 8080
- CONTACTS-MS : 8084
- CONTACTS-MS : 8086

---

## Resilience4j Configuration 

Resilience4j Circuit Breaker Configuration Circuit Breaker is implemented on USER-MS to handle failures when CONTACTS-MS becomes unavailable.

### Configuration 

```java
resilience4j:
  circuitbreaker:
   instances:
     userService:
      failure-rate-threshold: 50
      minimum-number-of-calls: 5
      permitted-number-of-calls-in-half-open-state: 3
      wait-duration-in-open-state: 20s
      automatic-transition-from-open-to-half-open-enabled: true
```

### Circuit Breaker States

- CLOSED (Normal Operation)
- OPEN (Fallback Activated)
- HALF_OPEN (Recovery Validation)

### Fallback Method

```java
@CircuitBreaker(
    name = "userService",
    fallbackMethod = "getUserByIdFallback")
```
Fallback response is returned instead of propagating the exception.

---

## Key Dependencies
   
#### RestTemplate
- spring-boot-starter-web

#### Eureka Server
- spring-cloud-starter-netflix-eureka-server

#### API Gateway
- spring-cloud-starter-gateway

#### Eureka Client
- spring-cloud-starter-netflix-eureka-client

#### Load Balancer
- spring-cloud-starter-loadbalancer

#### Resilience4j Circuit Breaker
- spring-cloud-starter-circuitbreaker-resilience4j

#### Database
- mysql-connector-j
- spring-boot-starter-data-jpa

---

## Database details
### USER-MS
Database: user_ms

Table: user

| Column     | Data Type    | Constraints |
|------------| -------------|-------------|
| user_id    | INT          | Primary Key |
| user_name  | VARCHAR(255) |             | 
| user_addr  | VARCHAR(255) |             |	

### CONTACTS-MS
Database: contacts_ms

Table: contacts

| Column    | Data Type     | Constraints       |
|---------- |---------------|-------------------|
| con_id    | INT           | Primary Key       |
| user_id   | INT           | User Identifier   |
| email     | VARCHAR(255)  | 
| phone     | VARCHAR(255)  | 

### Logical Relationship
- One User can have multiple Contacts.
- USER-MS and CONTACTS-MS maintain separate databases.
- No database-level foreign key relationship exists between services.
- CONTACTS-MS stores user_id only for data association.
- User and Contact information is combined through REST API communication.

--- 

## Documentation

Testing screenshots for request routing, load balancing across multiple instances and Resilience4j circuit breaker using RestTemplate added in 'RestTemplate-testing' document.

Available under the `docs` folder.

---

## Author

KanchanmB

GitHub:
https://github.com/kanchanmb01
