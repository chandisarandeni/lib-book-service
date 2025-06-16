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
}
