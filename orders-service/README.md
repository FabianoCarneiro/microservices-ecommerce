# Orders Service

This microservice is responsible for managing orders in the e-commerce application. It provides endpoints to create, retrieve, update, and delete orders.

## Features

- Create new orders
- Retrieve existing orders by ID or list all orders
- Update order details
- Delete orders

## Architecture

The Orders Service follows a microservices architecture and communicates with other services (Products and Payments) through a gateway. It uses Spring Data JPA for persistence and is built with Spring Boot.

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven

### Installation

1. Clone the repository:
   ```
   git clone <repository-url>
   ```

2. Navigate to the orders-service directory:
   ```
   cd orders-service
   ```

3. Build the project:
   ```
   mvn clean install
   ```

4. Run the application:
   ```
   mvn spring-boot:run
   ```

### Configuration

The application configuration can be found in `src/main/resources/application.yml`. Update the database connection details as needed.

### API Endpoints

- `POST /orders` - Create a new order
- `GET /orders` - Retrieve all orders
- `GET /orders/{id}` - Retrieve an order by ID
- `PUT /orders/{id}` - Update an existing order
- `DELETE /orders/{id}` - Delete an order by ID

## Testing

Unit tests are located in the `src/test/java/com/example/orders` directory. You can run the tests using:
```
mvn test
```

## License

This project is licensed under the MIT License. See the LICENSE file for details.