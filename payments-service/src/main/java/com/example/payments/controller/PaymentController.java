package com.example.payments.controller;

import com.example.payments.model.Payment;
import com.example.payments.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public List<Payment> findAll() {
        return paymentService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> findById(@PathVariable Long id) {
        return paymentService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/order/{orderId}")
    public List<Payment> findByOrderId(@PathVariable Long orderId) {
        return paymentService.findByOrderId(orderId);
    }

    @PostMapping
    public ResponseEntity<Payment> create(@Valid @RequestBody Payment payment) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.save(payment));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Payment> updateStatus(@PathVariable Long id,
                                                 @RequestParam Payment.PaymentStatus status) {
        return ResponseEntity.ok(paymentService.updateStatus(id, status));
    }
}