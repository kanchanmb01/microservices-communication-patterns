
## Prototype Objective

This prototype demonstrates service-to-service communication in a Spring Boot microservices architecture using OpenFeign. The project showcases Eureka Service Discovery, API Gateway routing, Client-Side Load Balancing, and Resilience4j Circuit Breaker implementation for fault tolerance.

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
- Feign Client


### Database
- MySQL

### Build Tool
- Maven

### Testing
- Browser

---

## Request Flow

```text
Browser
    ↓
API-GATEWAY (8083)
    ↓
USER-MS (8081)
    ↓
Feign Client
    ↓
CONTACTS-MS Instance
```
### Load Balancing Flow
```text
Browser
    ↓
API-GATEWAY (8083)
    ↓
USER-MS (8081)
    ↓
Feign Client
    ↓
Spring Cloud LoadBalancer
    ↓
Eureka Discovery Server
    ↓
CONTACTS-MS Instance
(8080 OR 8084 OR 8086)
```

## Features Implemented

- Service Discovery using Eureka
- API Routing using Spring Cloud Gateway
- Inter-Service Communication using Feign Client
- Client-Side Load Balancing using Spring Cloud OpenFeign and Eureka Discovery
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

Spring Cloud LoadBalancer is used with FeignClient to distribute requests across available service instances.

### FeignClient Configuration

```java
@SpringBootApplication
@EnableFeignClients
public class UserMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserMsApplication.class, args);
	}
}
```

### Contacts Service Instances

- CONTACTS-MS : 8080
- CONTACTS-MS : 8084
- CONTACTS-MS : 8086

---

## Resilience4j Configuration 

Circuit Breaker is implemented in USER-MS using Resilience4j and OpenFeign integration. It prevents repeated calls to unavailable CONTACTS-MS instances and returns fallback responses when failures occur.

### application.yml Configuration 

```java
spring:
   cloud:
    openfeign:
       circuitbreaker:
        # 1. Essential flag to route Feign through Resilience4j
         enabled: true 
resilience4j:
   circuitbreaker:
    instances:
        # 2. Must match the exact name property declared in @FeignClient
      contacts-ms:
          failureRateThreshold: 50
          minimumNumberOfCalls: 5
          permittedNumberOfCallsInHalfOpenState: 3
          waitDurationInOpenState: 20s
          automaticTransitionFromOpenToHalfOpenEnabled: true
```

### Circuit Breaker States

- CLOSED (Normal Operation)
- OPEN (Fallback Activated)
- HALF_OPEN (Recovery Validation)

### Circuit Breaker Lifecycle

CLOSED
 ↓
OPEN
 ↓
HALF_OPEN
 ↓
CLOSED

### Interface 

```java
@FeignClient(name="contacts-ms", fallback=ContactsUserFallback.class)
public interface ContactsUser {

	@GetMapping("/api/contacts/{userId}")
	List<Contacts> getContacstByUserId(@PathVariable int userId);

}
```

### Fallback class which implements Interface

```java
@Slf4j
@Component
public class ContactsUserFallback implements ContactsUser {
	@Override
	public List<Contacts> getContacstByUserId(int userId) {
		log.error("Contacts Service unavailable. Executing fallback.Please try again later...");
	    return new ArrayList<Contacts>();
	}
}
```
Fallback response is returned instead of propagating service availability failures to the client application.

---

## Key Dependencies

#### FeignClient
- spring-cloud-starter-openfeign

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

Testing screenshots for request routing, load balancing across multiple instances and Resilience4j circuit breaker using FeignClient added in 'FeignClient-testing' document.

---

## Author

KanchanmB

GitHub:
https://github.com/kanchanmb01
