# Microservices E-commerce Project

This project is a demonstration of a microservices architecture for an e-commerce application. It consists of three main microservices: Products, Orders, and Payments, along with a Gateway for communication between them.

## Project Structure

```
microservices-ecommerce
├── gateway
├── products-service
├── orders-service
└── payments-service
```

## Microservices Overview

### 1. Gateway
- **Purpose**: Acts as a single entry point for all client requests, routing them to the appropriate microservice.
- **Key Files**:
  - `GatewayApplication.java`: Entry point for the gateway microservice.
  - `application.yml`: Configuration settings for routing and service discovery.

### 2. Products Service
- **Purpose**: Manages product information, including CRUD operations for products.
- **Key Files**:
  - `ProductsApplication.java`: Entry point for the products microservice.
  - `ProductController.java`: Handles HTTP requests related to products.
  - `ProductService.java`: Contains business logic for managing products.
  - `ProductRepository.java`: Interface for CRUD operations on Product entities.
  - `Product.java`: Represents the product entity.

### 3. Orders Service
- **Purpose**: Manages order information, including CRUD operations for orders.
- **Key Files**:
  - `OrdersApplication.java`: Entry point for the orders microservice.
  - `OrderController.java`: Handles HTTP requests related to orders.
  - `OrderService.java`: Contains business logic for managing orders.
  - `OrderRepository.java`: Interface for CRUD operations on Order entities.
  - `Order.java`: Represents the order entity.

### 4. Payments Service
- **Purpose**: Manages payment information, including CRUD operations for payments.
- **Key Files**:
  - `PaymentsApplication.java`: Entry point for the payments microservice.
  - `PaymentController.java`: Handles HTTP requests related to payments.
  - `PaymentService.java`: Contains business logic for managing payments.
  - `PaymentRepository.java`: Interface for CRUD operations on Payment entities.
  - `Payment.java`: Represents the payment entity.

## Setup Instructions

1. **Clone the Repository**: 
   ```
   git clone <repository-url>
   ```

2. **Navigate to the Project Directory**: 
   ```
   cd microservices-ecommerce
   ```

3. **Build the Project**: 
   ```
   mvn clean install
   ```

4. **Run the Microservices**: 
   Each microservice can be run independently. Navigate to each service directory and run:
   ```
   mvn spring-boot:run
   ```

5. **Access the Gateway**: 
   The gateway will route requests to the appropriate microservices. You can access it at `http://localhost:8080`.

## Technologies Used
- Spring Boot
- Spring Data JPA
- Maven
- RESTful APIs

## Conclusion
This project serves as a foundational example of how to structure a microservices-based application using Spring Boot. Each microservice is independently deployable and can be scaled as needed.