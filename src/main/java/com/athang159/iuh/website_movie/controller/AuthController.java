package com.athang159.iuh.website_movie.controller;

import com.athang159.iuh.website_movie.dto.request.LoginRequest;
import com.athang159.iuh.website_movie.dto.response.JwtResponse;
import com.athang159.iuh.website_movie.entity.User;
import com.athang159.iuh.website_movie.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        return ResponseEntity.ok(authService.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest login, HttpServletResponse response) {
        String token = authService.login(login);

        ResponseCookie cookie = ResponseCookie.from("token", token)
                .httpOnly(true)
                .secure(false)  // true nếu bạn chạy HTTPS
                .path("/")
                .maxAge(7 * 24 * 60 * 60) // 7 ngày
                .sameSite("Lax")
                .build();

        response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return ResponseEntity.ok(Map.of("message", "Login successful", "token", token));
    }
}
