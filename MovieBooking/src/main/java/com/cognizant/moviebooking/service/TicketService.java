package com.cognizant.moviebooking.service;

import com.cognizant.moviebooking.model.Movie;
import com.cognizant.moviebooking.model.Tickets;

import java.util.Set;

public interface TicketService {

    public Set<Tickets> getAllTickets(int movieId);

    public boolean addTickets(Tickets tickets, int movieId);

    public boolean deleteTickets(int movieId);

    //public boolean updateTickets(Movie movie);


}
