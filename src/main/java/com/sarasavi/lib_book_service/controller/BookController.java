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

    // Add a new book
    @PostMapping(path = "/books/add")
    public BookDTO addBook(@RequestBody BookDTO bookDTO) {
        return bookService.addBook(bookDTO);
    }
}
