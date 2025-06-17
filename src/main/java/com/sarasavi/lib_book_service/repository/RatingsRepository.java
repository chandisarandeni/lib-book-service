package com.sarasavi.lib_book_service.repository;

import com.sarasavi.lib_book_service.entity.Ratings;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingsRepository extends JpaRepository<Ratings, Integer> {

    // find avg rating of a book
    @Query("SELECT AVG(r.stars) FROM Ratings r WHERE r.bookId = ?1")
    Double findAverageRatingByBookId(int bookId);

    // send average rating to Book Service
    @Modifying
    @Transactional
    @Query("UPDATE Book b SET b.ratings = :avgRating WHERE b.bookId = :bookId")
    int updateBookRating(@Param("bookId") int bookId, @Param("avgRating") Double avgRating);

}
