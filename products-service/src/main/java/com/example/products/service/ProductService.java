package com.example.products.service;

import com.example.products.model.Product;
import com.example.products.repository.ProductRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @CircuitBreaker(name = "products", fallbackMethod = "findAllFallback")
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<Product> findAllFallback(Exception e) {
        return List.of();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @CircuitBreaker(name = "products", fallbackMethod = "saveFallback")
    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product saveFallback(Product product, Exception e) {
        throw new RuntimeException("Product service temporarily unavailable", e);
    }

    public Product update(Long id, Product updated) {
        return productRepository.findById(id).map(p -> {
            p.setName(updated.getName());
            p.setDescription(updated.getDescription());
            p.setPrice(updated.getPrice());
            p.setStock(updated.getStock());
            return productRepository.save(p);
        }).orElseThrow(() -> new RuntimeException("Product not found: " + id));
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}