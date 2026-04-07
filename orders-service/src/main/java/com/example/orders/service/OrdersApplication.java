package com.example.orders.service;

import com.example.orders.model.Order;
import com.example.orders.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public Order updateStatus(Long id, Order.OrderStatus status) {
        return orderRepository.findById(id).map(o -> {
            o.setStatus(status);
            return orderRepository.save(o);
        }).orElseThrow(() -> new RuntimeException("Order not found: " + id));
    }

    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}