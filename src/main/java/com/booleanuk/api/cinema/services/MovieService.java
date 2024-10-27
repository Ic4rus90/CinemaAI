package com.booleanuk.api.cinema.services;

import com.booleanuk.api.cinema.models.Movie;
import com.booleanuk.api.cinema.models.Screening;
import com.booleanuk.api.cinema.repositories.MovieRepository;
import com.booleanuk.api.cinema.repositories.ScreeningRepository;
import com.booleanuk.api.cinema.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public Movie createMovie(Movie movie) {
        if (movie.getScreenings() != null) {
            for (Screening screening : movie.getScreenings()) {
                screening.setMovie(movie);
            }
        }
        return movieRepository.save(movie);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie updateMovie(Long id, Movie updatedMovie) {
        Movie existingMovie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No movie with that ID found"));

        if (updatedMovie.getTitle() != null) {
            existingMovie.setTitle(updatedMovie.getTitle());
        }
        if (updatedMovie.getRating() != null) {
            existingMovie.setRating(updatedMovie.getRating());
        }
        if (updatedMovie.getDescription() != null) {
            existingMovie.setDescription(updatedMovie.getDescription());
        }
        if (updatedMovie.getRuntimeMins() != null) {
            existingMovie.setRuntimeMins(updatedMovie.getRuntimeMins());
        }

        return movieRepository.save(existingMovie);
    }

    public Movie deleteMovie(Long id) {
        Movie existingMovie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No movie with that ID found"));

        movieRepository.delete(existingMovie);
        return existingMovie;
    }
}
