package com.cognizant.moviebooking.controller;

import com.cognizant.moviebooking.model.Movie;
import com.cognizant.moviebooking.model.Tickets;
import com.cognizant.moviebooking.service.MovieServiceImpl;
import com.cognizant.moviebooking.service.TicketsServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private MovieServiceImpl movieService;

    @Mock
    private TicketsServiceImpl ticketsService;

    @InjectMocks
    private TicketsController ticketsController;

    Movie movie1 = new Movie(1, "Citadel", "Pacific", 500);
    Movie movie2 = new Movie(2, "Fast X", "Pacific", 500);
    Movie movie3 = new Movie(3, "John Wick", "Pacific", 500);

    Tickets tickets1 = new Tickets(1, "Citadel", "Pacific", 200, 1);
    Tickets tickets2 = new Tickets(2, "Citadel", "Pacific", 200, 1);
    Tickets tickets3 = new Tickets(3, "Fast X", "Pacific", 200, 2);
    Tickets tickets4 = new Tickets(4, "John Wick", "Pacific", 200, 3);


    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(ticketsController).build();
    }

    @Test
    public void addTicket_success() throws Exception {
//        int movieId=1;
//        Tickets tickets1 = new Tickets();
//        tickets1.setBookedSeats(2);
//        Movie m = new Movie();
//        m.setMovieId(movieId);
//        m.setTotalSeats(10);
//        m.setAvailableSeats(10);
//        when(movieService.getMovieById(movieId)).thenReturn(m);
//        when(ticketsService.addTickets(tickets1,movieId)).thenReturn(true);
//        ResponseEntity<?> responseEntity = ticketsController.addTickets(movieId,tickets1);
//        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
//        assertEquals(responseEntity.getBody(),"Added");
        int movieId=1;
        when(movieService.getMovieById(movieId)).thenReturn(movie1);
        when(ticketsService.addTickets(tickets1,movieId)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/tickets/add/{mid}",movieId)
                        .header("Authorization", "Bearer token")
                        .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(tickets1)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()));
    }

    @Test
    public void addTicket_failure() throws Exception {
        Tickets t1 = new Tickets();
        int movieId=200;
        when(movieService.getMovieById(movieId)).thenReturn(null);
        when(ticketsService.addTickets(t1,movieId)).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/tickets/add/{mid}",movieId)
                        .header("Authorization", "Bearer token")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(t1)))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

}
