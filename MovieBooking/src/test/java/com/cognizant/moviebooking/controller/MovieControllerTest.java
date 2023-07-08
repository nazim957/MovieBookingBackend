package com.cognizant.moviebooking.controller;

import com.cognizant.moviebooking.exceptions.MovieIdNotExistsExceptions;
import com.cognizant.moviebooking.model.Movie;
import com.cognizant.moviebooking.model.Tickets;
import com.cognizant.moviebooking.repository.MovieRepository;
import static org.mockito.ArgumentMatchers.any;
import com.cognizant.moviebooking.service.MovieServiceImpl;
import com.cognizant.moviebooking.service.TicketsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private MovieServiceImpl movieService;

    @Mock
    private TicketsServiceImpl ticketsService;

    @InjectMocks
    private MovieController movieController;

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
        this.mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
    }

    List<Movie> movieList = new ArrayList<Movie>();

    @Test
    public void getAllMovies_success() throws Exception {
//        List<Movie> movieList = new ArrayList<>(Arrays.asList(movie1,movie2,movie3));
        movieList.add(movie1);
        movieList.add(movie2);
        movieList.add(movie3);
        when(movieService.getAllMovies()).thenReturn(movieList);

        List<Movie> mList = movieService.getAllMovies();
        assertEquals(movieList, mList);
//        mockMvc.perform(MockMvcRequestBuilders
//                .get("/api/v1/getAllMovies")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/getAllMovies")
                        .header("Authorization", "Bearer token")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)));

    }

    @Test
    public void getAllMovies_failure() throws Exception {

      //  List<Movie> mList = new ArrayList<>();
        when(movieService.getAllMovies()).thenReturn(null);

        //assertEquals((Double) null,movieList.size());
      //  assertEquals(0, movieList.size());
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/getAllMovies")
                        .header("Authorization", "Bearer token")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void getMovieById_success() throws Exception {
        when(movieService.getMovieById(movie1.getMovieId())).thenReturn(movie1);
        Movie m1 = movieService.getMovieById(1);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/getbyid/1")
                        .header("Authorization", "Bearer token")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.movieName", is("Citadel")));
    }

    @Test
    public void getMovieById_failure() throws Exception {
        int movieId = 100;
        when(movieService.getMovieById(movieId)).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/getbyid/{mid}",movieId)
                        .header("Authorization", "Bearer token")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void addMovie_success() throws Exception {

        List<Movie> mList = new ArrayList<>();
        mList.add(movie1);
        assertEquals(1,mList.size());
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/addMovie").contentType(MediaType.APPLICATION_JSON)
//                .content(new ObjectMapper().writeValueAsString(movie1))).andExpect(MockMvcResultMatchers.status().isCreated());

        when(movieService.addMovie(any())).thenReturn(movie1);
        String content = objectMapper.writeValueAsString(movie1);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/v1/addMovie")
                .header("Authorization", "Bearer token")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.movieName", is("Citadel")));
    }




    @Test
    public void addMovie_failure() throws Exception {

        when(movieService.addMovie(any())).thenReturn(null);

        Movie m1 = movieService.addMovie(null);
        assertNull(m1);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/addMovie").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(m1))).andExpect(MockMvcResultMatchers.status().is4xxClientError());

    }
//
//    @Test
//    public void deleteMovie_success() throws Exception{
//
//        int movieId = 1;
//        Movie m = new Movie(1,"Citadel","Pacific",500);
//        Tickets tickets1 = new Tickets(1, "Citadel", "Pacific", 200, 1);
//        movieService.addMovie(m);
//        ticketsService.addTickets(tickets1,1);
//
//        when(movieService.getMovieById(movieId)).thenReturn(m);
//
//        doNothing().when(movieService.deleteMovie(movieId));
//        doNothing().when(ticketsService.deleteTickets(movieId));
//
//
//        when(movieService.deleteMovie(movieId) && ticketsService.deleteTickets(1)).thenReturn(true);
//        mockMvc.perform(MockMvcRequestBuilders
//        .delete("/api/v1/deleteMovieById/{mid}",movieId)
//                        .header("Authorization", "Bearer token")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//
//    }

    @Test
    public void deleteMovie_success() throws Exception {
        int movieId = 1;
        when(movieService.deleteMovie(movieId)).thenReturn(true);
        when(ticketsService.deleteTickets(movieId)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/v1/deleteMovieById/{mid}", movieId)
                        .header("Authorization", "Bearer your-token-value") // Set a valid token
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
                //.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Movie deleted successfully"));
    }


//    @Test
//    public void deleteMovie_failure() throws Exception {
//        int movieId=10;
//        when(movieService.getMovieById(movieId)).thenReturn(null);
//                mockMvc.perform(MockMvcRequestBuilders
//                .get("/api/v1/deleteMovieById/{mid}",movieId)
//                                        .header("Authorization", "Bearer token")
//                        )
//
//                .andExpect(status().isInternalServerError());
//
//    }

    @Test
    public void deleteMovie_failure() throws Exception {
        int movieId = 123;


        when(movieService.deleteMovie(movieId)).thenReturn(false);
        when(ticketsService.deleteTickets(movieId)).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/v1/deleteMovieById/{mid}", movieId)
                        .header("Authorization", "Bearer your-token-value") // Set a valid token
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
              //  .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Failed to Delete Movie !!"));
    }


//    @Test
//    public void deleteMovie_failure() throws Exception {
//        int movieId = 400;
//        when(movieService.deleteMovie(movieId)).thenReturn(false);
//        mockMvc.perform(MockMvcRequestBuilders
//                .get("/api/v1/deleteMovieById/{mid}",movieId)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isInternalServerError());
//    }

    @Test
    public void getMovieByNameSucess() throws Exception {
        String movieName = "John Wick";
        when(movieService.getMovieByName(movieName)).thenReturn(movie3);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/getbyName/{movieName}",movieName)
                        .header("Authorization", "Bearer token")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void getMovieByNamefailure() throws Exception {
        String movieName = "Movie";
        when(movieService.getMovieByName(movieName)).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/getbyName/{movieName}",movieName)
                        .header("Authorization", "Bearer token")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

    }


}
