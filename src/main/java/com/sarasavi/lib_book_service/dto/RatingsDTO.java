package com.sarasavi.lib_book_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingsDTO {
    private int ratingId;
    private int bookId;
    private int memberId;
    private int stars;
    private String review;
    private String dateOfRating;
}
