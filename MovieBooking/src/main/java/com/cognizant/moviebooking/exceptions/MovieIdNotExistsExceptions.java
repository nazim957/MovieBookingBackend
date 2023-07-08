package com.cognizant.moviebooking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason="Movie Id Not Exist Exception raised- handled by custom exception")
public class MovieIdNotExistsExceptions extends Exception {

}
