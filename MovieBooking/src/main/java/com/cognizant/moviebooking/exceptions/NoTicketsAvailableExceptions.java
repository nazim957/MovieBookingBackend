package com.cognizant.moviebooking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason="Ticktes Exception raised- handled by custom exception")

public class NoTicketsAvailableExceptions extends Exception{
}
