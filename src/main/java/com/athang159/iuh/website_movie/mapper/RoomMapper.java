package com.athang159.iuh.website_movie.mapper;

import com.athang159.iuh.website_movie.dto.response.RoomDetailResponse;
import com.athang159.iuh.website_movie.dto.response.RoomResponse;
import com.athang159.iuh.website_movie.entity.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = SeatMapper.class)
public interface RoomMapper {
    RoomResponse toRoomResponse(Room room);
    List<RoomResponse> toRoomResponse(List<Room> room);

    RoomDetailResponse toRoomDetailResponse(Room room);
}
