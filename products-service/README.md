# Products Microservice

This microservice is responsible for managing products in the e-commerce application. It provides endpoints to create, read, update, and delete product information.

## Features

- **CRUD Operations**: Supports creating, retrieving, updating, and deleting products.
- **Spring Data JPA**: Utilizes Spring Data JPA for database interactions.
- **RESTful API**: Exposes a RESTful API for product management.

## Endpoints

- `GET /products`: Retrieve a list of all products.
- `GET /products/{id}`: Retrieve a specific product by its ID.
- `POST /products`: Create a new product.
- `PUT /products/{id}`: Update an existing product.
- `DELETE /products/{id}`: Delete a product by its ID.

## Configuration

The application configuration can be found in `src/main/resources/application.yml`. This file contains database connection details and other service-specific settings.

## Running the Application

To run the products microservice, use the following command:

```
mvn spring-boot:run
```

Ensure that you have the necessary database setup as specified in the configuration file.

## Testing

Unit tests for the products microservice can be found in the `src/test/java/com/example/products` directory. You can run the tests using:

```
mvn test
```

## Dependencies

The microservice uses the following key dependencies:

- Spring Boot Starter Web
- Spring Boot Starter Data JPA
- H2 Database (for in-memory testing)

## Author

This microservice is developed as part of a study project to understand microservices architecture using Spring Boot.