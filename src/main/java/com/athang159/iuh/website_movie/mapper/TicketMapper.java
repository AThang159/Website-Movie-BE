package com.athang159.iuh.website_movie.mapper;

import com.athang159.iuh.website_movie.dto.response.TicketResponse;
import com.athang159.iuh.website_movie.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring" , uses = SeatStatusMapper.class)
public interface TicketMapper {
    TicketResponse toTicketResponse(Ticket ticket);
    List<TicketResponse> toTicketResponseList(List<Ticket> tickets);
}
