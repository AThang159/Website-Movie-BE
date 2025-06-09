package com.athang159.iuh.website_movie.dto.request;

import com.athang159.iuh.website_movie.enums.FormatType;
import com.athang159.iuh.website_movie.enums.TheaterStatus;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TheaterRequest {
    private String name;
    private String address;
    private String city;
    private FormatType format;
    private TheaterStatus status;
}
