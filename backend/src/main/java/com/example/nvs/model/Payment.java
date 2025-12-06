package com.example.nvs.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;
    private String currency = "INR";
    private String status;      // CREATED, PAID, FAILED
    private LocalDateTime createdAt = LocalDateTime.now();
}
