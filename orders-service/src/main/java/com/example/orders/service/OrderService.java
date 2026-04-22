package com.example.orders.service;

import com.example.orders.model.Order;
import com.example.orders.repository.OrderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @CircuitBreaker(name = "orders", fallbackMethod = "findAllFallback")
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public List<Order> findAllFallback(Exception e) {
        return List.of();
    }

    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @CircuitBreaker(name = "orders", fallbackMethod = "saveFallback")
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public Order saveFallback(Order order, Exception e) {
        throw new RuntimeException("Order service temporarily unavailable", e);
    }

    @CircuitBreaker(name = "orders", fallbackMethod = "updateStatusFallback")
    public Order updateStatus(Long id, Order.OrderStatus status) {
        return orderRepository.findById(id).map(o -> {
            o.setStatus(status);
            return orderRepository.save(o);
        }).orElseThrow(() -> new RuntimeException("Order not found: " + id));
    }

    public Order updateStatusFallback(Long id, Order.OrderStatus status, Exception e) {
        throw new RuntimeException("Order service temporarily unavailable", e);
    }

    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}