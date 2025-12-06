package com.example.nvs.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username; // or email

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role; // "ROLE_USER" or "ROLE_ADMIN"
}
