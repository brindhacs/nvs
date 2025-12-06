package com.example.nvs.service;

import com.example.nvs.model.Payment;
import com.example.nvs.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository repo;

    public PaymentService(PaymentRepository repo) {
        this.repo = repo;
    }

    // Create a payment record
    public Payment createPayment(double amount) {
        Payment p = new Payment();
        p.setAmount(amount);
        p.setStatus("CREATED");
        return repo.save(p);
    }

    // Confirm payment
    public Payment confirmPayment(Long paymentId) {
        Payment p = repo.findById(paymentId)
                .orElseThrow(() -> new IllegalArgumentException("Payment not found with ID: " + paymentId));
        p.setStatus("PAID");
        return repo.save(p);
    }

    // Get all payments
    public List<Payment> getAllPayments() {
        return repo.findAll();
    }

    // Get payment by ID
    public Payment getPaymentById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Payment not found with ID: " + id));
    }

    // Update payment (amount or status)
    public Payment updatePayment(Long id, Payment updatedPayment) {
        Payment existing = getPaymentById(id);
        existing.setAmount(updatedPayment.getAmount());
        existing.setStatus(updatedPayment.getStatus());
        return repo.save(existing);
    }

    // Delete payment
    public void deletePayment(Long id) {
        repo.deleteById(id);
    }
}
