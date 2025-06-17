package com.sarasavi.lib_book_service.repository;

import com.sarasavi.lib_book_service.entity.Ratings;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingsRepository extends JpaRepository<Ratings, Integer> {

    // find avg rating of a book
    @Query("SELECT AVG(r.stars) FROM Ratings r WHERE r.bookId = ?1")
    Double findAverageRatingByBookId(int bookId);


    // find ratings by member id
    @Query("SELECT r FROM Ratings r WHERE r.memberId = ?1")
    List<Ratings> findRatingsByMemberId(int memberId);

}
