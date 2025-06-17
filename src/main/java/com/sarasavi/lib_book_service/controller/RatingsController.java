package com.sarasavi.lib_book_service.controller;

import com.sarasavi.lib_book_service.dto.RatingsDTO;
import com.sarasavi.lib_book_service.service.RatingsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/")
public class RatingsController {

    @Autowired
    private RatingsService ratingsService;

    @Autowired
    private ModelMapper modelMapper;


    // Add a new rating
    @PostMapping(path = "/ratings/add")
    public RatingsDTO addRating(@RequestBody RatingsDTO ratingsDTO) {
        return ratingsService.addRating(ratingsDTO);
    }
}
