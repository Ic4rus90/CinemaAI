package com.booleanuk.api.cinema.controllers;

import com.booleanuk.api.cinema.models.Screening;
import com.booleanuk.api.cinema.services.ScreeningService;
import com.booleanuk.api.cinema.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies/{movieId}/screenings")
public class ScreeningController {
    @Autowired
    private ScreeningService screeningService;

    @PostMapping
    public ResponseEntity<ApiResponse> createScreening(@PathVariable Long movieId, @RequestBody Screening screening) {
        Screening createdScreening = screeningService.createScreening(movieId, screening);
        return new ResponseEntity<>(new ApiResponse("success", createdScreening), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getScreenings(@PathVariable Long movieId) {
        return new ResponseEntity<>(new ApiResponse("success", screeningService.getScreeningsByMovieId(movieId)), HttpStatus.OK);
    }
}
