package com.athang159.iuh.website_movie.controller.user;

import com.athang159.iuh.website_movie.dto.response.ApiResponse;
import com.athang159.iuh.website_movie.dto.response.UserResponse;
import com.athang159.iuh.website_movie.security.JwtUtil;
import com.athang159.iuh.website_movie.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("userControllerUser")
@RequestMapping("/api/user")
@Tag(name = "User", description = "APIs for user profile and authentication")
public class UserController {

    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    @Operation(summary = "Get current user profile", description = "Returns profile information of the authenticated user.")
    public ResponseEntity<ApiResponse<UserResponse>> getUserProfile(Authentication authentication) {
        String username = authentication.getName();
        UserResponse user = userService.getUserByUsername(username);
        return ResponseEntity.ok(new ApiResponse<>(true, "Fetch user successfully", user));
    }
}
