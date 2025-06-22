package com.sarasavi.lib_book_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "book")
public class Book {

    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int bookId;

    @Column(name = "book_name")
    String bookName;

    @Column(name = "author")
    String author;

    @Column(name = "publisher")
    String publisher;

    @Column(name = "date_of_publication")
    String dateOfPublication;

    @Column(name = "isbn")
    String isbn;

    @Column(name = "language")
    String language;

    @Column(name = "category")
    String category;

    @Column(name = "genre")
    String genre;

    @Column(name = "description")
    String description;

    @Column(name = "image_url")
    String imageUrl;

    @Column(name = "quantity")
    int quantity;

    @Column(name = "number_of_viewers")
    int numberOfViewers;

    @Column(name = "number_of_readers")
    int numberOfReaders;

    @Column(name = "ratings")
    double ratings;

    @Column(name = "ratings_updated_by")
    LocalDate ratingsUpdatedBy;
}

// Documentation for frontend developers:
// ----------- quantity: enter actual quantity of the book
// ----------- numberOfViewers: must be initialized to 0
// ----------- numberOfReaders: must be initialized to 0
// ----------- ratings: must be initialized to 0.0
// ----------- ratingsUpdatedBy: must be current date
