package com.example.nvs.controller;

import com.example.nvs.model.Payment;
import com.example.nvs.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "*")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // Get all payments
    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    // Get payment by ID
    @GetMapping("/{id}")
    public Payment getPaymentById(@PathVariable Long id) {
        return paymentService.getPaymentById(id);
    }

    // Create a payment (temporary, without Razorpay)
    @PostMapping
    public Payment createPayment(@RequestParam double amount) {
        return paymentService.createPayment(amount);
    }

    // Confirm payment
    @PutMapping("/{id}/confirm")
    public Payment confirmPayment(@PathVariable Long id) {
        return paymentService.confirmPayment(id);
    }

    // Update payment (amount or status)
    @PutMapping("/{id}")
    public Payment updatePayment(@PathVariable Long id, @RequestBody Payment updatedPayment) {
        return paymentService.updatePayment(id, updatedPayment);
    }

    // Delete payment
    @DeleteMapping("/{id}")
    public String deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return "Payment with ID " + id + " deleted successfully.";
    }
}
