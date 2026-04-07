# Payments Microservice

This is the Payments microservice of the microservices-ecommerce project. It is responsible for handling payment processing for orders placed in the system.

## Features

- Create, read, update, and delete payment records.
- Integration with the Orders microservice to process payments related to specific orders.
- RESTful API for payment operations.

## Endpoints

- `GET /payments`: Retrieve all payments.
- `GET /payments/{id}`: Retrieve a specific payment by ID.
- `POST /payments`: Create a new payment.
- `PUT /payments/{id}`: Update an existing payment.
- `DELETE /payments/{id}`: Delete a payment.

## Technologies Used

- Spring Boot
- Spring Data JPA
- H2 Database (for development and testing)
- Maven for dependency management

## Setup Instructions

1. Clone the repository:
   ```
   git clone <repository-url>
   ```

2. Navigate to the payments-service directory:
   ```
   cd payments-service
   ```

3. Build the project using Maven:
   ```
   mvn clean install
   ```

4. Run the application:
   ```
   mvn spring-boot:run
   ```

5. Access the API at `http://localhost:8080/payments`.

## Testing

Unit tests are included in the `src/test/java/com/example/payments` directory. You can run the tests using:
```
mvn test
```

## License

This project is licensed under the MIT License. See the LICENSE file for details.