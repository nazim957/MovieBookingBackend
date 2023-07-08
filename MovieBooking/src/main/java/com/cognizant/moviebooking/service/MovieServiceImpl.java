package com.cognizant.moviebooking.service;

import com.cognizant.moviebooking.exceptions.MovieIdAlreadyExistsExceptions;
import com.cognizant.moviebooking.exceptions.MovieIdNotExistsExceptions;
import com.cognizant.moviebooking.model.Movie;
import com.cognizant.moviebooking.model.Tickets;
import com.cognizant.moviebooking.repository.MovieRepository;
import com.cognizant.moviebooking.repository.TicketsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TicketsRepository ticketsRepository;

    @Override
    public List<Movie> getAllMovies() {

        List<Movie> movieList = movieRepository.findAll();
        if (movieList != null && movieList.size() > 0) {
             return movieList;
        }
        return null;
    }

    @Override
    public Movie addMovie(Movie movie) throws MovieIdAlreadyExistsExceptions {
        Optional<Movie> opMovie = movieRepository.findById(movie.getMovieId());
        if (opMovie.isPresent()) {
          //  System.out.println("Here is error");
            throw new MovieIdAlreadyExistsExceptions();
        } else
          //  System.out.println("Come here");
            movie.setAvailableSeats(movie.getTotalSeats());
            return movieRepository.saveAndFlush(movie);
    }

//    @Override
//    public boolean updateMovie(Movie movie) {
//
//       // Movie movie1 = movieRepository.findById(movie.getMovieId());
//        Movie movie1 = movieRepository.getById(movie.getMovieId());
//        if(movie1!=null)
//        {
//           // movie1.setMovieId(movie.getMovieId());
//            movie1.setMovieName(movie.getMovieName());
//            movie1.setTheaterName(movie.getTheaterName());
//            movie1.setTotalSeats(movie.getTotalSeats());
//            movieRepository.saveAndFlush(movie1);
//            return true;
//        }
//        return false;
//    }

    @Override
    public boolean deleteMovie(int mid) throws MovieIdNotExistsExceptions {
        Optional<Movie> byId = movieRepository.findById(mid);
        if(byId.isPresent()) {
            movieRepository.deleteById(mid);
            return true;

        }
        else {
            throw new MovieIdNotExistsExceptions();
        }
    }

    @Override
    public Movie getMovieById(int mid) {
        if(movieRepository.findById(mid).isPresent()) {
            return movieRepository.findById(mid).get();
        }
        else
            return null;
    }

    @Override
    public Movie getMovieByName(String movieName) {
        return movieRepository.findByMovieName(movieName);
    }

    @Override
    public List<Tickets> getAllBookedTicketsByName(String movieName) {
       // return (List<Tickets>) movieRepository.findByMovieName(movieName);
        return ticketsRepository.findByMovieName(movieName);
    }

    @Override
    public List<Tickets> getAllBookedTickets() {
        List<Tickets> ticketList = ticketsRepository.findAll();
        if (ticketList != null && ticketList.size() > 0) {
            return ticketList;
        }
        return null;

    }


}
