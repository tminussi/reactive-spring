package com.javaonthecloud.movie.service;

import com.javaonthecloud.movie.model.Movie;
import com.javaonthecloud.movie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by thales on 09/01/2018.
 */
@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Mono<Movie> fetchMovieById(String id) {
        return this.movieRepository.findById(id);
    }

    public Flux<Movie> fetchAllMovies() {
        return this.movieRepository.findAll();
    }

    public Mono<Movie> create(Movie movie) {
        return this.movieRepository.save(movie);
    }
}
