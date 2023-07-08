package com.cognizant.moviebooking.service;

import com.cognizant.moviebooking.exceptions.MovieIdAlreadyExistsExceptions;
import com.cognizant.moviebooking.model.Movie;
import com.cognizant.moviebooking.model.Tickets;
import com.cognizant.moviebooking.repository.MovieRepository;
import com.cognizant.moviebooking.repository.TicketsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@AutoConfigureMockMvc
@SpringBootTest
public class TicketsServiceImplTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieServiceImpl movieService;

    @Mock
    private TicketsRepository ticketsRepository;

    @InjectMocks
    private TicketsServiceImpl ticketsService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    Movie movie1 = new Movie(1, "Citadel", "Pacific", 500);
    Movie movie2 = new Movie(2, "Fast X", "Pacific", 500);
    Movie movie3 = new Movie(3, "John Wick", "Pacific", 500);

    Tickets tickets1 = new Tickets(1, "Citadel", "Pacific", 200, 1);
    Tickets tickets2 = new Tickets(2, "Citadel", "Pacific", 200, 1);
    Tickets tickets3 = new Tickets(3, "Fast X", "Pacific", 200, 2);
    Tickets tickets4 = new Tickets(4, "John Wick", "Pacific", 200, 3);


    @Test
    void getAllTickets_Success() {
        // Arrange
        int movieId = 1; // Assuming a valid movie ID
        Set<Tickets> expectedTicketsList = new HashSet<>(); // Assuming some Tickets objects are returned
        when(ticketsRepository.getTicketsList(anyInt())).thenReturn(expectedTicketsList);

        Set<Tickets> actualTicketsList = ticketsService.getAllTickets(movieId);

        assertEquals(expectedTicketsList, actualTicketsList);
    }

    @Test
    void getAllTicketsfailure() {

        int movieId = 3;
        when(ticketsRepository.getTicketsList(anyInt())).thenReturn(null);
        Set<Tickets> ticketsList = ticketsService.getAllTickets(movieId);
        assertNull(ticketsList);
    }

    @Test
    void deleteTickets_success() {
        int movieId = 123; // Assuming a valid movie ID
        boolean result = ticketsService.deleteTickets(movieId);

        assertTrue(result);
        verify(ticketsRepository, times(1)).deleteTicketsData(movieId);
    }

//    @Test
//    void deleteTickets_Failure_ReturnsFalse() throws Exception {
//        int movieId = 3;
//        Mockito.doThrow(NullPointerException.class).when(ticketsRepository).deleteTicketsData(anyInt());
//        boolean result = ticketsService.deleteTickets(movieId);
//        assertFalse(result);
//        verify(ticketsRepository, times(1)).deleteTicketsData(movieId);
//    }

    @Test
    void addTickets_Success_ReturnsTrue() {
        Tickets tickets = new Tickets();
        tickets.setBookedSeats(2);

        int movieId = 123;
        Movie movie = new Movie();
        movie.setMovieId(movieId);
        movie.setAvailableSeats(5);
        movie.setTotalSeats(10);
        when(movieRepository.getById(movieId)).thenReturn(movie);
        boolean result = ticketsService.addTickets(tickets, movieId);
        assertTrue(result);
    }

    @Test
    void addTickets_Failure_ReturnsFalse() {

        Tickets tickets = new Tickets();
        tickets.setBookedSeats(8);

        int movieId = 123;
        Movie movie = new Movie();
        movie.setMovieId(movieId);
        movie.setAvailableSeats(5);
        movie.setTotalSeats(10);

        when(movieRepository.getById(movieId)).thenReturn(movie);
        boolean result = ticketsService.addTickets(tickets, movieId);
        assertFalse(result);
    }


//    @Test
//    public void getAllTickets() throws MovieIdAlreadyExistsExceptions {
//        int movieId=1;
//        List<Movie> movieList=new ArrayList<>();
//        movieList.add(movie1);
//        Set<Tickets> ticketsList = new HashSet<>();
//        ticketsList.add(tickets1);
//        for(Movie m : movieList)
//        {
//            m.setTicketsList(ticketsList);
//        }
//
//        doNothing().when(movieService.addMovie(movie1)).thenReturn(movie1);
//        doNothing().when(ticketsService.addTickets(tickets1,movieId)).thenReturn(true);
//        when(ticketsRepository.getTicketsList(movieId)).thenReturn(ticketsList);
//        Set<Tickets> ticketsList1 = ticketsService.getAllTickets(movieId);
//        assertEquals(ticketsList,ticketsList1);
//    }
}
