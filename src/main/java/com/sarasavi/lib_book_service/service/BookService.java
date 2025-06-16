package com.sarasavi.lib_book_service.service;

import com.sarasavi.lib_book_service.dto.BookDTO;
import com.sarasavi.lib_book_service.entity.Book;
import com.sarasavi.lib_book_service.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Get all books
    public List<BookDTO> getAllBooks() {
        List<Book> bookList = bookRepository.findAll();
        return modelMapper.map(bookList, new TypeToken<List<BookDTO>>() {
        }.getType());
    }

    // Get book by ID
    public BookDTO getBookById(int bookId) {
        Book book = bookRepository.findById(bookId).get();
        return modelMapper.map(book, BookDTO.class);
    }

    // Add a new book
    public BookDTO addBook(BookDTO bookDTO) {
        Book book = modelMapper.map(bookDTO, Book.class);
        Book savedBook = bookRepository.save(book);
        return modelMapper.map(savedBook, BookDTO.class);
    }

    // Update an existing book
    public BookDTO updateBook(int bookId, BookDTO bookDTO) {
        Book existingBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // Ensure ID is not overwritten
        bookDTO.setBookId(bookId);
        modelMapper.map(bookDTO, existingBook);

        return modelMapper.map(bookRepository.save(existingBook), BookDTO.class);
    }

    // Delete a book
    public void deleteBook(int bookId) {
        if (bookRepository.existsById(bookId)) {
            bookRepository.deleteById(bookId);
        } else {
            throw new RuntimeException("Book not found");
        }
    }
}
