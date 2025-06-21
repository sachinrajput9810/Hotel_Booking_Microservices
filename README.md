# 🏨 Hotel Management Microservices Project

A scalable microservices-based Hotel Rating and User Review system built using Spring Boot and Spring Cloud. This project demonstrates core microservices concepts like service discovery, centralized configuration, API gateway routing, and inter-service communication.

---

## 🚀 Tech Stack

- **Java 17**
- **Spring Boot**
- **Spring Cloud**
  - Eureka Discovery Server
  - Spring Cloud Gateway
  - Spring Cloud Config Server
- **Spring Data JPA**
- **MySQL / H2**
- **Lombok**
- **Maven**

---

## 📦 Microservices Architecture

### 1. 🔐 API Gateway
- Single entry point for client requests
- Uses Spring Cloud Gateway for routing
- Automatically routes based on service name via Eureka

### 2. 📘 Config Server
- Centralized configuration for all services
- Loads properties from remote Git repo
- Git Repo: [Config Repo](https://github.com/sachinrajput9810/Microservice_Config_Hotel_Management)

### 3. 🧭 Eureka Discovery Server
- Service registry for dynamic discovery
- Each microservice registers itself here

### 4. 👤 User Service
- Manages user-related operations (CRUD)
- Connects with Rating Service to fetch user reviews

### 5. 🏨 Hotel Service
- Manages hotel details and data (CRUD)
- Invoked by Rating Service

### 6. ⭐ Rating Service
- Handles rating creation and retrieval
- Combines user and hotel data

---

## 🔄 Service Communication Flow

1. All services register with **Eureka Discovery Server**
2. Client hits the **API Gateway**
3. API Gateway routes the request to the appropriate microservice (via Eureka)
4. Services talk to each other using:
   - `RestTemplate`
   - `FeignClient` (optional future enhancement)
