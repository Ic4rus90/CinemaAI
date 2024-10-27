package com.booleanuk.api.cinema.controllers;

import com.booleanuk.api.cinema.models.Movie;
import com.booleanuk.api.cinema.services.MovieService;
import com.booleanuk.api.cinema.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<ApiResponse> createMovie(@RequestBody Movie movie) {
        Movie createdMovie = movieService.createMovie(movie);
        return new ResponseEntity<>(new ApiResponse("success", createdMovie), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllMovies() {
        return new ResponseEntity<>(new ApiResponse("success", movieService.getAllMovies()), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
        Movie updatedMovie = movieService.updateMovie(id, movie);
        return new ResponseEntity<>(new ApiResponse("success", updatedMovie), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteMovie(@PathVariable Long id) {
        Movie deletedMovie = movieService.deleteMovie(id);
        return new ResponseEntity<>(new ApiResponse("success", deletedMovie), HttpStatus.OK);
    }
}
