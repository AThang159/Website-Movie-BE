package com.athang159.iuh.website_movie.service;

import com.athang159.iuh.website_movie.dto.response.UserResponse;
import com.athang159.iuh.website_movie.entity.User;
import com.athang159.iuh.website_movie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public List<UserResponse> getAllUsers();
    public UserResponse getUserById(Long id);
    public UserResponse updateUser(Long id, User updatedUser);
    public void deleteUser(Long id);
    public Long countUsers();
}
