package com.cognizant.moviebooking.service;

import com.cognizant.moviebooking.exceptions.MovieIdAlreadyExistsExceptions;
import com.cognizant.moviebooking.exceptions.MovieIdNotExistsExceptions;
import com.cognizant.moviebooking.model.Movie;
import com.cognizant.moviebooking.repository.MovieRepository;
import org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willDoNothing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@AutoConfigureMockMvc
@SpringBootTest
public class MovieServiceImplTests {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieServiceImpl movieService;

    @Autowired
    private MockMvc mockMvc;


    @BeforeEach
    public void init()
    {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(movieService).build();

    }



    @Test
    public void addMovieSuccess() throws Exception
    {

        Movie movie = new Movie(1, "Citadel", "Pacific", 500);
//        movieService.addMovie(movie1);
//        verify(movieRepository,times(1)).saveAndFlush(movie1);


        given(movieRepository.findById(movie.getMovieId()))
                .willReturn(Optional.empty());

        given(movieRepository.saveAndFlush(movie)).willReturn(movie);

        Movie savedMovie = movieService.addMovie(movie);

        assertEquals(movie,savedMovie);


//        when(movieRepository.saveAndFlush(any())).thenReturn(movie);
//
//        Movie m1 = movieService.addMovie(movie);
//        assertEquals(movie,m1);

    }

//    @Test
//    public void addMovieFailure() throws Exception
//    {
//
//        when(movieRepository.saveAndFlush(any())).thenReturn(null);
//
//        Movie m1 = movieService.addMovie(null);
//        assertNull(m1);
//
//    }

    @Test
    void addMovieFailure() throws Exception {
        Movie existingMovie = new Movie();
        existingMovie.setMovieId(123);
        when(movieRepository.findById(existingMovie.getMovieId())).thenReturn(Optional.of(existingMovie));

        assertThrows(MovieIdAlreadyExistsExceptions.class, () -> {
            movieService.addMovie(existingMovie);
        });
    }

    @Test
    public void getAllMovieSuccess() throws Exception
    {
        List<Movie> movieList = new ArrayList<>();
        Movie movie1 = new Movie(1, "Citadel", "Pacific", 500);
        Movie movie2 = new Movie(2, "Fast X", "Pacific", 500);
        Movie movie3 = new Movie(3, "John Wick", "Pacific", 500);
        movieList.add(movie1);
        movieList.add(movie2);
        movieList.add(movie3);

        when(movieRepository.findAll()).thenReturn(movieList);

        List<Movie> movieList1 = movieService.getAllMovies();

        assertEquals(movieList,movieList1);
    }

    @Test
    public void getAllMoviesFailure() throws Exception
    {
        when(movieRepository.findAll()).thenReturn(null);

        List<Movie> movieList1 = movieService.getAllMovies();
        assertNull(movieList1);
    }

    @Test
    public void deleteMoviesSuccess() throws Exception {
        Movie movie = new Movie();
        movie.setMovieId(1);
        movie.setMovieName("Pathan");
        movie.setTheaterName("Pacific");
        movie.setTotalSeats(100);


        when(movieRepository.findById(movie.getMovieId())).thenReturn(Optional.of(movie));

        boolean flag = movieService.deleteMovie(1);
        assertEquals(true,flag);

//        willDoNothing().given(movieRepository).deleteById(movie.getMovieId());
//
//        movieService.deleteMovie(movie.getMovieId());
//
//        verify(movieRepository, times(1)).deleteById(movie.getMovieId());
    }

    @Test
    public void deleteMoviefailure() throws MovieIdNotExistsExceptions {
        Movie movie = new Movie();
        when(movieRepository.findById(movie.getMovieId())).thenReturn(Optional.empty());
//       boolean flag =  movieService.deleteMovie(movie.getMovieId());
//        assertEquals(false,flag);
        assertThrows(MovieIdNotExistsExceptions.class, () -> {
            movieService.deleteMovie(movie.getMovieId());
        });
    }

    @Test
    public void getMovieByIdSuccess()
    {
        List<Movie> movieList = new ArrayList<>();
        Movie movie1 = new Movie(1, "Citadel", "Pacific", 500);
        Movie movie2 = new Movie(2, "Fast X", "Pacific", 500);
        Movie movie3 = new Movie(3, "John Wick", "Pacific", 500);
        movieList.add(movie1);
        movieList.add(movie2);
        movieList.add(movie3);
        when(movieRepository.findById(1)).thenReturn(Optional.of(movie1));

         Movie m =  movieService.getMovieById(1);

        assertEquals("Citadel",m.getMovieName());

    }

    @Test
    public void getMovieByNameSuccess()
    {
        List<Movie> movieList = new ArrayList<>();
        Movie movie1 = new Movie(1, "Citadel", "Pacific", 500);
        Movie movie2 = new Movie(2, "Fast X", "Pacific", 500);
        Movie movie3 = new Movie(3, "John Wick", "Pacific", 500);
        movieList.add(movie1);
        movieList.add(movie2);
        movieList.add(movie3);

        when(movieRepository.findByMovieName("John Wick")).thenReturn(movie3);
        Movie m = movieService.getMovieByName("John Wick");
        assertEquals("John Wick",m.getMovieName());
    }


}
