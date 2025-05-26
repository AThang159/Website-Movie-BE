package com.athang159.iuh.website_movie.dto.response;

import lombok.Data;
import java.util.List;

@Data
public class TheaterChainWithTheatersResponse {
    private Long theaterChainId;
    private String theaterChainName;
    private List<TheaterInfo> theaters;

    @Data
    public static class TheaterInfo {
        private Long theaterId;
        private String theaterName;
    }
}
