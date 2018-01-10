package com.javaonthecloud.movie.handler;

import com.javaonthecloud.movie.exception.MovieException;
import com.javaonthecloud.movie.model.Movie;
import com.javaonthecloud.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.function.Consumer;

import static org.springframework.web.reactive.function.server.ServerResponse.badRequest;
import static org.springframework.web.reactive.function.server.ServerResponse.created;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * Created by thales on 09/01/2018.
 */
@Component
public class MovieHandler {

    private final MovieService movieService;

    @Autowired
    public MovieHandler(MovieService movieService) {
        this.movieService = movieService;
    }

    public Mono<ServerResponse> fetchMovieById(ServerRequest request) {
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.movieService.fetchMovieById(request.pathVariable("id")), Movie.class);
    }

    public Mono<ServerResponse> fetchAllMovies(ServerRequest request) {
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.movieService.fetchAllMovies(), Movie.class);
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        Mono<Movie> movieMono = this.movieService.create(request.bodyToMono(Movie.class).block());
        return created(URI.create("/api/movies/"))
                .contentType(MediaType.APPLICATION_JSON)
                .body(movieMono, Movie.class);
    }
}
