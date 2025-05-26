package com.athang159.iuh.website_movie.repository;

import com.athang159.iuh.website_movie.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, Long>, ShowtimeRepositoryCustom {
    List<Showtime> findByTheaterRoomIdAndShowDateOrderByStartTime(Long theaterRoomId, LocalDate showDate);

//    @Query("SELECT s FROM Showtime s WHERE " +
//            "(:theaterRoomId IS NULL OR s.theaterRoom.id = :theaterRoomId) AND " +
//            "(:showDate IS NULL OR s.showDate = :showDate) AND " +
//            "(:theaterId IS NULL OR s.theater.id = :theaterId) AND " +
//            "(:movieId IS NULL OR s.movie.movieId = :movieId)")
//    List<Showtime> findByFilters(@Param("movieId") String movieId,
//                                 @Param("showDate") LocalDate showDate,
//                                 @Param("theaterId") Long theaterId,
//                                 @Param("theaterRoomId") Long theaterRoomId);

    List<Showtime> findByTheaterRoomIdAndShowDate(Long theaterRoomId, LocalDate showDate);
}
