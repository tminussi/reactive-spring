package com.javaonthecloud.movie.repository;

import com.javaonthecloud.movie.model.Movie;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * Created by thales on 09/01/2018.
 */
public interface MovieRepository extends ReactiveMongoRepository<Movie, String> {
}
