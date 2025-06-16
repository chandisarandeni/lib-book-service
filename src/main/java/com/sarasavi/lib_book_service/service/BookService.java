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
    // increase by 1: numberOfViewers when this function is called
    public BookDTO getBookById(int bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // Increment the number of viewers
        book.setNumberOfViewers(book.getNumberOfViewers() + 1);
        bookRepository.save(book);

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

    //------ Additional advanced services added here: like searching, filtering, etc. ------

    // Filter books by: genre
    public List<BookDTO> filterBooksByGenre(String genre) {
        List<Book> books = bookRepository.findBooksByGenre(genre);
        if (books.isEmpty()) {
            throw new RuntimeException("No books found for the specified genre");
        }
        return modelMapper.map(books, new TypeToken<List<BookDTO>>() {
        }.getType());
    }

    // Filter books by: category
    public List<BookDTO> filterBooksByCategory(String category) {
        List<Book> books = bookRepository.findBooksByCategory(category);
        if (books.isEmpty()) {
            throw new RuntimeException("No books found for the specified category");
        }
        return modelMapper.map(books, new TypeToken<List<BookDTO>>() {
        }.getType());
    }

    // Filter books by: popularity (numberOfReaders)
    // Filter top 10 most popular books
    public List<BookDTO> filterBooksByPopularity() {
        List<Book> books = bookRepository.findBooksByPopularity();
        if (books.isEmpty()) {
            throw new RuntimeException("No books found");
        }
        // Limit to top 10 most popular books
        List<Book> topBooks = books.stream().limit(10).toList();
        return modelMapper.map(topBooks, new TypeToken<List<BookDTO>>() {
        }.getType());
    }


}
