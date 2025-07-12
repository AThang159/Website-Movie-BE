package com.athang159.iuh.website_movie.controller.admin;

import com.athang159.iuh.website_movie.dto.response.ApiResponse;
import com.athang159.iuh.website_movie.dto.response.UserResponse;
import com.athang159.iuh.website_movie.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("userControllerAdmin")
@RequestMapping("/api/admin/users")
@Tag(name = "Admin - User Management", description = "APIs for managing users from admin panel")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    @Operation(summary = "Get all users", description = "Returns a list of all users for admin")
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAllUsers() {
        List<UserResponse> users = userService.getAllUsers();
        return ResponseEntity.ok(ApiResponse.success("Danh sách người dùng", users));
    }

    @DeleteMapping("/soft-delete/{username}")
    @Operation(summary = "Soft delete user", description = "Marks a user as deleted by username (admin only)")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable String username) {
        userService.softDeleteUser(username);
        return ResponseEntity.ok(ApiResponse.success("Xóa người dùng thành công", null));
    }
}
