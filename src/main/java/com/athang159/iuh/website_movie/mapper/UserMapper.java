package com.athang159.iuh.website_movie.mapper;

import com.athang159.iuh.website_movie.dto.response.UserResponse;
import com.athang159.iuh.website_movie.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toUserResponse(User user);
    List<UserResponse> toUsersResponse(List<User> users);
}
