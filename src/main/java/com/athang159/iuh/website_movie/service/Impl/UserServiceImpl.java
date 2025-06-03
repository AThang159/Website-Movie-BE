package com.athang159.iuh.website_movie.service.Impl;

import com.athang159.iuh.website_movie.dto.response.UserResponse;
import com.athang159.iuh.website_movie.entity.User;
import com.athang159.iuh.website_movie.mapper.UserMapper;
import com.athang159.iuh.website_movie.repository.UserRepository;
import com.athang159.iuh.website_movie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserResponse> getAllUsers(){
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponses = userMapper.toUsersResponse(users);
        return userResponses;
    }

    @Override
    public UserResponse getUserById(Long id){
        return userMapper.toUserResponse(userRepository.findById(id).orElse(null));
    };

    @Override
    public UserResponse updateUser(Long id, User updatedUser) {
        User userUpdate = userRepository.findById(id).map(user -> {
            user.setUsername(updatedUser.getUsername());
            user.setPassword(updatedUser.getPassword());
            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());
            user.setEmail(updatedUser.getEmail());
            user.setPhone(updatedUser.getPhone());
            user.setRole(updatedUser.getRole());
            user.setStatus(updatedUser.getStatus());
            userRepository.save(user);
            return user;
        }).orElseThrow(() -> new RuntimeException("User not found with id " + id));
        return userMapper.toUserResponse(userUpdate);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
