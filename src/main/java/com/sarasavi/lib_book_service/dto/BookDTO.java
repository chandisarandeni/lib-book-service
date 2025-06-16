package com.sarasavi.lib_book_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private int bookId;
    private String bookName;
    private String author;
    private String publisher;
    private String dateOfPublication;
    private String isbn;
    private String language;
    private String category;
    private String genre;
    private String description;
    private String imageUrl;
    private int quantity;
    private int numberOfViewers;
    private int numberOfReaders;
}
