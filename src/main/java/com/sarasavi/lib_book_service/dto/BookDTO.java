package com.sarasavi.lib_book_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    int bookId;
    String bookName;
    String author;
    String publisher;
    String dateOfPublication;
    String isbn;
    String language;
    String category;
    String genre;
    String description;
    String imageUrl;
    int quantity;
    int numberOfViewers;
    int numberOfReaders;
}
