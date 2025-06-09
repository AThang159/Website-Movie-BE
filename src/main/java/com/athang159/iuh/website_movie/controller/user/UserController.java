package com.athang159.iuh.website_movie.controller.user;

import com.athang159.iuh.website_movie.dto.response.ApiResponse;
import com.athang159.iuh.website_movie.dto.response.UserResponse;
import com.athang159.iuh.website_movie.security.JwtUtil;
import com.athang159.iuh.website_movie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("userControllerUser")
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<UserResponse>> getUserProfile(@CookieValue("token") String token) {
        String username = jwtUtil.extractUsername(token);
        return ResponseEntity.ok(new ApiResponse<>(true, "Fetch user successlly", userService.getUserByUsername(username)));
    }

}
