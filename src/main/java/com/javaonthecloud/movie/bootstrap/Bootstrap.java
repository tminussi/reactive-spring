package com.javaonthecloud.movie.bootstrap;

import com.javaonthecloud.movie.model.Movie;
import com.javaonthecloud.movie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

/**
 * Created by thales on 09/01/2018.
 */
@Component
public class Bootstrap implements CommandLineRunner {

    private final MovieRepository movieRepository;

    @Autowired
    public Bootstrap(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        this.movieRepository
                .deleteAll()
                .thenMany(Flux
                .just("Movie 1", "Movie 2", "Movie 3")
                .map(Movie::new))
                .flatMap(movieRepository::save)
                .subscribe(null, null, () -> this.movieRepository.findAll().subscribe(System.out::println));
    }
}
