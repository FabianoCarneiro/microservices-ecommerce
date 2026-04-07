# Microservices E-commerce Gateway

This is the gateway microservice for the Microservices E-commerce project. The gateway acts as a single entry point for all client requests and routes them to the appropriate microservices (Products, Orders, and Payments).

## Features

- **Routing**: The gateway routes requests to the respective microservices based on the URL patterns.
- **Load Balancing**: It can distribute incoming requests across multiple instances of the microservices.
- **Service Discovery**: Integrates with a service discovery mechanism to dynamically route requests to available service instances.

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven 3.6 or higher

### Installation

1. Clone the repository:
   ```
   git clone <repository-url>
   cd microservices-ecommerce/gateway
   ```

2. Build the project:
   ```
   mvn clean install
   ```

3. Run the gateway:
   ```
   mvn spring-boot:run
   ```

### Configuration

The gateway configuration can be found in `src/main/resources/application.yml`. You can customize routing, service discovery, and other settings as needed.

### Testing

Unit tests for the gateway can be found in `src/test/java/com/example/gateway/GatewayApplicationTests.java`. You can run the tests using:
```
mvn test
```

## API Documentation

Refer to the individual microservice documentation for details on the available endpoints and their usage.

## Contributing

Contributions are welcome! Please submit a pull request or open an issue for any enhancements or bug fixes.

## License

This project is licensed under the MIT License. See the LICENSE file for details.