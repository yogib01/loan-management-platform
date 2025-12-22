package com.lmp.loanmanagement.auth.controller;

import com.lmp.loanmanagement.auth.dto.AuthResponse;
import com.lmp.loanmanagement.auth.dto.LoginRequest;
import com.lmp.loanmanagement.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {

        String token = authService.login(
                request.getUsername(),
                request.getPassword()
        );

        return ResponseEntity.ok(new AuthResponse(token));
    }
}
