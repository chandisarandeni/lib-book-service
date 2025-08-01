package com.sarasavi.lib_book_service.repository;

import com.sarasavi.lib_book_service.dto.BookDTO;
import com.sarasavi.lib_book_service.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    //--------- Advanced Queries Implemented Here ---------

    // Filter books by: genre
    @Query("SELECT b FROM Book b WHERE b.genre = ?1")
    List<Book> findBooksByGenre(String genre);

    // Filter books by: category
    @Query("SELECT b FROM Book b WHERE b.category = ?1")
    List<Book> findBooksByCategory(String category);

    // Filter books by: popularity (numberOfReaders)
    @Query("SELECT b FROM Book b ORDER BY b.numberOfReaders DESC")
    List<Book> findBooksByPopularity();

    // Filter trending books (that includes -> most number of readers + most number of viewers )
    @Query("SELECT b FROM Book b ORDER BY b.numberOfReaders + b.numberOfViewers DESC")
    List<Book> findTrendingBooks();

    // Filter books by: New Arrived (by -> published date)
    @Query("SELECT b FROM Book b ORDER BY b.dateOfPublication DESC")
    List<Book> findNewArrivedBooks();

    // search books by : bookName , author, keyword: that include parts of the book name
    @Query("SELECT b FROM Book b WHERE b.bookName LIKE %?1% OR b.author LIKE %?1%")
    List<Book> searchBooks(String keyword);

    // update book ratings
    @Query("UPDATE Book b SET b.ratings = ?2 WHERE b.bookId = ?1")
    int updateBookRating(int bookId, Double avgRating);

    // find the highest rated books of the month
    @Query("SELECT b FROM Book b " +
            "WHERE FUNCTION('MONTH', b.ratingsUpdatedBy) = FUNCTION('MONTH', CURRENT_DATE) " +
            "AND FUNCTION('YEAR', b.ratingsUpdatedBy) = FUNCTION('YEAR', CURRENT_DATE) " +
            "ORDER BY b.ratings DESC")
    List<Book> findHighestRatedBooksOfMonth();


}
