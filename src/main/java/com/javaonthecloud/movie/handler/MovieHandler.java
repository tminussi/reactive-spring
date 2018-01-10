package com.javaonthecloud.movie.handler;

import static org.springframework.web.reactive.function.server.ServerResponse.created;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import com.javaonthecloud.movie.model.Movie;
import com.javaonthecloud.movie.service.MovieService;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

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
        return
            this.movieService
                .create(request.bodyToMono(Movie.class).block())
                .flatMap(movie -> ServerResponse.created(URI.create("/api/movies/" + movie.getId())).body(Mono.just(movie), Movie.class))
                .switchIfEmpty(ServerResponse.badRequest().build());
    }
}
