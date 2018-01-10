package com.javaonthecloud.movie.web;

import com.javaonthecloud.movie.handler.MovieHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * Created by thales on 09/01/2018.
 */
@Configuration
public class Router {

    @Bean
    public RouterFunction<ServerResponse> routes(MovieHandler movieHandler) {
        return
                nest(path("/api/movies"), route(GET("/").and(accept(APPLICATION_JSON)), movieHandler::fetchAllMovies)
                        .andRoute(GET("/{id}").and(accept(APPLICATION_JSON)), movieHandler::fetchMovieById)
                        .andRoute(POST("/").and(contentType(APPLICATION_JSON)), movieHandler::create));
    }
}
