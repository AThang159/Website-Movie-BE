package com.athang159.iuh.website_movie.controller;

import com.athang159.iuh.website_movie.config.JwtProperties;
import com.athang159.iuh.website_movie.dto.request.LoginRequest;
import com.athang159.iuh.website_movie.dto.response.ApiResponse;
import com.athang159.iuh.website_movie.entity.User;
import com.athang159.iuh.website_movie.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "APIs for user registration, login, and logout")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    JwtProperties jwtProperties;

    @PostMapping("/register")
    @Operation(summary = "Register new user", description = "Registers a new user with provided credentials and user details.")
    public ResponseEntity<?> register(@RequestBody User user) {
        return ResponseEntity.ok(authService.register(user));
    }

    @PostMapping("/login")
    @Operation(summary = "Login user", description = "Authenticates the user and returns a JWT token in cookie.")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody LoginRequest login, HttpServletResponse response) {
        String token = authService.login(login);

        ResponseCookie cookie = ResponseCookie.from("token", token)
                .httpOnly(true)
                .secure(jwtProperties.isCookieSecure())
                .path("/")
                .maxAge(7 * 24 * 60 * 60)
                .sameSite(jwtProperties.getCookieSameSite())
                .build();

        response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return ResponseEntity.ok(new ApiResponse<>(true, "Login successful", token));
    }

    @GetMapping("/logout")
    @Operation(summary = "Logout user", description = "Clears the JWT token from browser cookie.")
    public ResponseEntity<ApiResponse<String>> logout(HttpServletResponse response) {
        ResponseCookie cookie = ResponseCookie.from("token", "")
                .httpOnly(true)
                .secure(jwtProperties.isCookieSecure())
                .path("/")
                .maxAge(0)
                .sameSite(jwtProperties.getCookieSameSite())
                .build();

        response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return ResponseEntity.ok(new ApiResponse<>(true, "Logout successful", null));
    }

}
