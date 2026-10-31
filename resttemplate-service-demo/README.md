
## Architecture

This prototype demonstrates inter-service communication using RestTemplate in a microservices architecture.

### Components

- API Gateway
- Eureka Discovery Server
- User Service
- Income Service
- MySQL Database

---

## Technology Stack
### Backend
- Java 17
- Spring Boot 4.0.8
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
INCOME-MS
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
INCOME-MS Instance
(8080 OR 8084 OR 8086)
```

### Features Implemented

- Service Discovery using Eureka
- API Routing using Spring Cloud Gateway
- Inter-Service Communication using RestTemplate
- Client Side Load Balancing
- Circuit Breaker using Resilience4j
- Multiple Income Service Instances

---

## Eureka Configuration

Eureka Server acts as a centralized service registry.All microservices register themselves with Eureka during startup.
It enables dynamic service discovery among microservices.

### Registered Services

- API-GATEWAY
- USER-MS
- INCOME-MS

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

### Income Service Instances
- INCOME-MS : 8080
- INCOME-MS : 8084
- INCOME-MS : 8086

---

## Resilience4j Configuration 

Resilience4j Circuit Breaker Configuration Circuit Breaker is implemented on USER-MS to handle failures when INCOME-MS becomes unavailable.

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
    Eureka server- 
    - spring-cloud-starter-netflix-eureka-server
    API Gateway-
    - spring-cloud-starter-gateway
    USER-MS
    - spring-cloud-starter-netflix-eureka-client
    - spring-cloud-starter-circuitbreaker-resilience4j
    INCOME-MS
    - spring-cloud-starter-netflix-eureka-client

## Note
Same 'Users' and 'Income' database tables used from expense tracker project. Expense tracker project available on GitHub at https://github.com/kanchanmb01/expense-tracker 

## Documentation

Testing screenshots for requst routing, load balancing across multiple instances and Resilience4j circuit breaker using RestTemplate added in 'RestTemplate-testing' document.

Available under the `documents` folder.


## Author

KanchanmB

GitHub:
https://github.com/kanchanmb01
