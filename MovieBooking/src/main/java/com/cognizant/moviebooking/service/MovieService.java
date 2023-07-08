package com.cognizant.moviebooking.service;

import com.cognizant.moviebooking.exceptions.MovieIdAlreadyExistsExceptions;
import com.cognizant.moviebooking.exceptions.MovieIdNotExistsExceptions;
import com.cognizant.moviebooking.model.Movie;
import com.cognizant.moviebooking.model.Tickets;

import java.util.List;

public interface MovieService {

    public List<Movie> getAllMovies();

    public Movie addMovie(Movie movie) throws MovieIdAlreadyExistsExceptions;

    //public boolean updateMovie(Movie movie);

    public boolean deleteMovie(int mid) throws MovieIdNotExistsExceptions;

    public Movie getMovieById(int mid);

    public Movie getMovieByName(String movieName);

    public List<Tickets> getAllBookedTicketsByName(String movieName);

    public List<Tickets> getAllBookedTickets();

}
