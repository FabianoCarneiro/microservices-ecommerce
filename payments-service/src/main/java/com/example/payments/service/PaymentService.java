package com.example.payments.service;

import com.example.payments.model.Payment;
import com.example.payments.repository.PaymentRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @CircuitBreaker(name = "payments", fallbackMethod = "findAllFallback")
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    public List<Payment> findAllFallback(Exception e) {
        return List.of();
    }

    public Optional<Payment> findById(Long id) {
        return paymentRepository.findById(id);
    }

    public List<Payment> findByOrderId(Long orderId) {
        return paymentRepository.findByOrderId(orderId);
    }

    @CircuitBreaker(name = "payments", fallbackMethod = "saveFallback")
    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Payment saveFallback(Payment payment, Exception e) {
        throw new RuntimeException("Payment service temporarily unavailable", e);
    }

    @CircuitBreaker(name = "payments", fallbackMethod = "updateStatusFallback")
    public Payment updateStatus(Long id, Payment.PaymentStatus status) {
        return paymentRepository.findById(id).map(p -> {
            p.setStatus(status);
            return paymentRepository.save(p);
        }).orElseThrow(() -> new RuntimeException("Payment not found: " + id));
    }

    public Payment updateStatusFallback(Long id, Payment.PaymentStatus status, Exception e) {
        throw new RuntimeException("Payment service temporarily unavailable", e);
    }
}