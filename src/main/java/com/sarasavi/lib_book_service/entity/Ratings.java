package com.sarasavi.lib_book_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "ratings")
public class Ratings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private int ratingId;

    @Column(name = "book_id")
    private int bookId;

    @Column(name = "member_id")
    private int memberId;

    @Column(name = "stars")
    private int stars;

    @Column(name = "review")
    private String review;

    @Column(name = "date_of_rating")
    private String dateOfRating;
}
