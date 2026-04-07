package com.example.payments.service;

import com.example.payments.model.Payment;
import com.example.payments.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    public Optional<Payment> findById(Long id) {
        return paymentRepository.findById(id);
    }

    public List<Payment> findByOrderId(Long orderId) {
        return paymentRepository.findByOrderId(orderId);
    }

    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Payment updateStatus(Long id, Payment.PaymentStatus status) {
        return paymentRepository.findById(id).map(p -> {
            p.setStatus(status);
            return paymentRepository.save(p);
        }).orElseThrow(() -> new RuntimeException("Payment not found: " + id));
    }
}