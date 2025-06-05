package com.athang159.iuh.website_movie.service;

import com.athang159.iuh.website_movie.dto.response.JwtResponse;
import com.athang159.iuh.website_movie.entity.User;
import com.athang159.iuh.website_movie.dto.request.LoginRequest;
import com.athang159.iuh.website_movie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

public interface AuthService {
    public User register(User user);
    public String login(LoginRequest request);
}
