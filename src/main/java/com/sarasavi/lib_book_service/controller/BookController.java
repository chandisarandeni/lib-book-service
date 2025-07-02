package com.sarasavi.lib_book_service.controller;

import com.sarasavi.lib_book_service.dto.BookDTO;
import com.sarasavi.lib_book_service.entity.Book;
import com.sarasavi.lib_book_service.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/")
public class BookController {

    @Autowired
    private BookService bookService;

    // Get all books
    @GetMapping(path = "/books")
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    // Get book by ID
    @GetMapping(path = "/books/{bookId}")
    public BookDTO getBookById(@PathVariable("bookId") int bookId) {
        return bookService.getBookById(bookId);
    }

    // Add a new book
    @PostMapping(path = "/books/add")
    public BookDTO addBook(@RequestBody BookDTO bookDTO) {
        return bookService.addBook(bookDTO);
    }

    // Update an existing book
    @PutMapping(path = "/books/update/{bookId}")
    public BookDTO updateBook(@PathVariable("bookId") int bookId, @RequestBody BookDTO bookDTO) {
        return bookService.updateBook(bookId, bookDTO);
    }

    // Delete a book
    @DeleteMapping(path = "/books/delete/{bookId}")
    public void deleteBook(@PathVariable("bookId") int bookId) {
        bookService.deleteBook(bookId);
    }

    //------ Count related controllers added here: like total number of books, etc. ------

    // Count total number of books
    @GetMapping(path = "/books/count")
    public long countTotalBooks() {
        return bookService.countTotalBooks();
    }

    //------ Additional advanced controllers added here: like searching, filtering, etc. ------

    // Filter books by: genre
    @GetMapping(path = "/books", params = {"genre"})
    public List<BookDTO> filterBooksByGenre(@RequestParam("genre") String genre) {
        return bookService.filterBooksByGenre(genre);
    }

    // Filter books by: category
    @GetMapping(path = "/books", params = {"category"})
    public List<BookDTO> filterBooksByCategory(@RequestParam("category") String category) {
        return bookService.filterBooksByCategory(category);
    }

    // Filter books by: popularity (numberOfReaders)
    @GetMapping(path = "/books/popular")
    public List<BookDTO> filterBooksByPopularity() {
        return bookService.filterBooksByPopularity();
    }

    // Filter trending books
    @GetMapping(path = "/books/trending")
    public List<BookDTO> filterTrendingBooks() {
        return bookService.filterTrendingBooks();
    }

    // Filter new arrived books
    @GetMapping(path = "/books/new-arrivals")
    public List<BookDTO> filterNewArrivedBooks() {
        return bookService.filterNewArrivedBooks();
    }

    // search books by : bookName , author, keyword: that include parts of the book name
    @GetMapping(path = "/books/search")
    public List<BookDTO> searchBooks(@RequestParam("keyword") String keyword) {
        return bookService.searchBooks(keyword);
    }

    //  filter the highest rated books of the month
    @GetMapping(path = "/books/books-of-the-month")
    public List<BookDTO> filterHighestRatedBooksOfMonth() {
        return bookService.filterHighestRatedBooksOfMonth();
    }


    // ------------------------------ controllers called by Ratings Service ------------------------------
    // update book ratings
    @PutMapping(path = "/books/{bookId}/ratings")
    public BookDTO updateBookRatings(@PathVariable("bookId") int bookId, @RequestParam("avgRating") double avgRating) {
        return bookService.updateBookRatings(bookId, avgRating);
    }
}
