package com.javaonthecloud.movie.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by thales on 10/01/2018.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MovieException extends RuntimeException {
}
