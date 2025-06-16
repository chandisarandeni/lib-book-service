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
}
