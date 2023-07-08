package com.cognizant.moviebooking.service;

import com.cognizant.moviebooking.model.Movie;
import com.cognizant.moviebooking.model.Tickets;
import com.cognizant.moviebooking.repository.MovieRepository;
import com.cognizant.moviebooking.repository.TicketsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TicketsServiceImpl implements TicketService {

    @Autowired
    private TicketsRepository ticketsRepository;

    @Autowired
    private MovieRepository movieRepository;

    int availableSeats;


    @Override
    public Set<Tickets> getAllTickets(int movieId) {

        Set<Tickets> ticketsList = ticketsRepository.getTicketsList(movieId);
        return ticketsList;
    }
    
//    @Override
//    public boolean addTickets(Tickets tickets, Movie movie) {
//        Tickets ticketsObj = new Tickets();
//        availableSeats=movie.getAvailableSeats();
//
//        if(availableSeats-tickets.getBookedSeats()>=0) {
//            ticketsObj.setMovieName(tickets.getMovieName());
//            ticketsObj.setTheaterName(tickets.getTheaterName());
//            ticketsObj.setBookedSeats(tickets.getBookedSeats());
//            ticketsObj.setMovie_id_fk(tickets.getMovie_id_fk());
//            availableSeats=availableSeats-tickets.getBookedSeats();
//            ticketsObj.setAvailableSeats(availableSeats);
//            ticketsObj.setTotalSeats(movie.getTotalSeats());
//            movie.setAvailableSeats(availableSeats);
//            ticketsRepository.saveAndFlush(ticketsObj);
//            return true;
//        }
//        System.out.println("NOT POSSIBLE");
//        return false;
//    }

    @Override
    public boolean addTickets(Tickets tickets, int movieId) {
        Tickets ticketsObj = new Tickets();
        Movie movie = movieRepository.getById(movieId);
        availableSeats=movie.getAvailableSeats();

        if(availableSeats-tickets.getBookedSeats()>=0) {
            ticketsObj.setMovieName(movie.getMovieName());
            ticketsObj.setTheaterName(movie.getTheaterName());
            ticketsObj.setBookedSeats(tickets.getBookedSeats());
            ticketsObj.setMovie_id_fk(movie.getMovieId());
            availableSeats=availableSeats-tickets.getBookedSeats();
            ticketsObj.setAvailableSeats(availableSeats);
            ticketsObj.setTotalSeats(movie.getTotalSeats());
            movie.setAvailableSeats(availableSeats);
            ticketsRepository.saveAndFlush(ticketsObj);
            return true;
        }
        System.out.println("NOT POSSIBLE");
        return false;
    }



    @Override
    public boolean deleteTickets(int movieId) {

        ticketsRepository.deleteTicketsData(movieId);
        return true;
    }

//    @Override
//    public boolean updateTickets(int movieId) {
//        Optional<Movie> byId = movieRepository.findById(movieId);
//        return false;
 //   }

}

