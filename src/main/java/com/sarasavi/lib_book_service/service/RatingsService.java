package com.sarasavi.lib_book_service.service;

import com.sarasavi.lib_book_service.dto.RatingsDTO;
import com.sarasavi.lib_book_service.entity.Ratings;
import com.sarasavi.lib_book_service.repository.RatingsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RatingsService {

    @Autowired
    private RatingsRepository ratingsRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RestTemplate restTemplate;

    // add new rating
    public RatingsDTO addRating(RatingsDTO ratingsDTO) {
        // 1. Save the new rating
        Ratings ratings = modelMapper.map(ratingsDTO, Ratings.class);
        Ratings savedRatings = ratingsRepository.save(ratings);

        // 2. Calculate the new average rating for the book
        Double avgRating = ratingsRepository.findAverageRatingByBookId(savedRatings.getBookId());

        // 3. Build the URI with query parameter
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080/api/v1/books/{bookId}/ratings")
                .queryParam("avgRating", avgRating)
                .buildAndExpand(savedRatings.getBookId())
                .toUri();

        // 4. Send PUT request to Book Service
        try {
            restTemplate.exchange(uri, HttpMethod.PUT, null, Void.class);
        } catch (RestClientException e) {
            System.err.println("Failed to update book rating: " + e.getMessage());
        }

        return modelMapper.map(savedRatings, RatingsDTO.class);
    }


}
