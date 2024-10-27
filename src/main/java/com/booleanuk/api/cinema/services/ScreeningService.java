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
public class ScreeningService {
    @Autowired
    private ScreeningRepository screeningRepository;

    @Autowired
    private MovieRepository movieRepository;

    public Screening createScreening(Long movieId, Screening screening) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("No movie with that ID found"));

        screening.setMovie(movie);
        return screeningRepository.save(screening);
    }

    public List<Screening> getScreeningsByMovieId(Long movieId) {
        if (!movieRepository.existsById(movieId)) {
            throw new ResourceNotFoundException("No movie with that ID found");
        }
        return screeningRepository.findByMovieId(movieId);
    }
}
