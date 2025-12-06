package com.example.nvs.controller;

import com.example.nvs.model.User;
import com.example.nvs.security.JwtUtil;
import com.example.nvs.service.AuthService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(AuthService authService, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest req) {
        String role = req.getRole() != null ? req.getRole() : "ROLE_USER";
        User created = authService.signup(req.getUsername(), req.getPassword(), role);
        // Optionally return token immediately:
        String token = jwtUtil.generateToken(created.getUsername(), created.getRole());
        return ResponseEntity.ok(new JwtResponse(token, created.getUsername(), created.getRole()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        try {
            User user = authService.findByUsername(req.getUsername());
            if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
                return ResponseEntity.status(401).body("Invalid username or password");
            }
            String token = jwtUtil.generateToken(user.getUsername(), user.getRole());
            return ResponseEntity.ok(new JwtResponse(token, user.getUsername(), user.getRole()));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }

    @Data
    static class SignupRequest {
        private String username;
        private String password;
        private String role;
    }

    @Data
    static class LoginRequest {
        private String username;
        private String password;
    }

    @Data
    static class JwtResponse {
        private final String token;
        private final String username;
        private final String role;
    }
}
