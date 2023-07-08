package com.cognizant.moviebooking.controller;

import com.cognizant.moviebooking.exceptions.MovieIdAlreadyExistsExceptions;
import com.cognizant.moviebooking.exceptions.MovieIdNotExistsExceptions;
import com.cognizant.moviebooking.filter.JwtToken;
import com.cognizant.moviebooking.model.Movie;
import com.cognizant.moviebooking.model.Tickets;
import com.cognizant.moviebooking.response.ResponseHandler;
import com.cognizant.moviebooking.service.MovieService;
import com.cognizant.moviebooking.service.TicketService;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private TicketService ticketService;

//    @Autowired
//    private KafkaTemplate<String, Object> kafkaTemplate;
//
//    @Autowired
//    private NewTopic topic;

    private static final Logger LOGGER = Logger.getLogger(MovieController.class.getName());
    @GetMapping("/getAllMovies")
    public ResponseEntity<?> getAllMovies(@RequestHeader("Authorization") @JwtToken String jwtToken)
    {
        LOGGER.info("Received request to get all movies");
        List<Movie> movieList = movieService.getAllMovies();
        if(movieList!=null)
        {
            for(Movie m : movieList)
            {
                Set<Tickets> ticketsList = ticketService.getAllTickets(m.getMovieId());
                m.setTicketsList(ticketsList);
            }
            LOGGER.info("Showing movies");
           // kafkaTemplate.send(topic.name(),movieList);
            return new ResponseEntity<List<Movie>>(movieList, HttpStatus.OK);
        }
        LOGGER.info("MovieList is Empty!!");
      //  kafkaTemplate.send(topic.name(),"MovieList is Empty!! ");
        return new ResponseEntity<String>("MovieList is Empty!!", HttpStatus.NO_CONTENT);

    }

    @PostMapping("/addMovie")
    public ResponseEntity<?> addMovie(@RequestHeader("Authorization") @JwtToken String jwtToken ,@RequestBody Movie movie) throws MovieIdAlreadyExistsExceptions
    {
        LOGGER.info("Received request to add movies");
//        Tickets tickets = new Tickets();
        if(movieService.addMovie(movie)!=null)
        {
           // System.out.println("Inside");
//            tickets.setMovie_id_fk(movie.getMovieId());
//            tickets.setMovieName(movie.getMovieName());
//            tickets.setTheaterName(movie.getTheaterName());
//            tickets.setTotalSeats(movie.getTotalSeats());
//            tickets.setAvailableSeats(movie.getAvailableSeats());
           // kafkaTemplate.send(topic.name()," New Movie Added. " + "Movie Details are: "+ movie);
            return new ResponseEntity<Movie>(movie,HttpStatus.CREATED);
        }
       // System.out.println("Outside");
      //  kafkaTemplate.send(topic.name(),"Movie object is null! ");
        return new ResponseEntity<String>("Movie object is null!", HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/deleteMovieById/{mid}")
    public ResponseEntity<?> deleteMovie(@RequestHeader("Authorization") @JwtToken String jwtToken, @PathVariable("mid") int mid) throws MovieIdNotExistsExceptions {
        LOGGER.info("Received request to delete movie");
        if (movieService.deleteMovie(mid) && ticketService.deleteTickets(mid)) {
         //   kafkaTemplate.send(topic.name(),"Movie Deleted successfully. ");
            return ResponseHandler.generateResponse("Movie deleted successfully",HttpStatus.OK);
        }
       // kafkaTemplate.send(topic.name(),"Failed to Delete Movie!! ");
        return ResponseHandler.generateResponse("Failed to Delete Movie !!",HttpStatus.INTERNAL_SERVER_ERROR);
    }



    @GetMapping("/getbyid/{mid}")
    public ResponseEntity<?> getMovieById(@RequestHeader("Authorization") @JwtToken String jwtToken , @PathVariable("mid") int mid)
    {
        LOGGER.info("Received request to get movie by id");
        Movie movieexist = movieService.getMovieById(mid);


        if(movieexist!=null) {
            Set<Tickets> ticketsList = ticketService.getAllTickets(mid);
            movieexist.setTicketsList(ticketsList);

         //   kafkaTemplate.send(topic.name(),movieexist);
            return new ResponseEntity<Movie>(movieexist,HttpStatus.OK);
        }
      //  kafkaTemplate.send(topic.name(),"Movie Record Missing. ");
        return new ResponseEntity<String>("Movie record missing", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getbyName/{movieName}")
    public ResponseEntity<?> getByMovieName(@RequestHeader("Authorization") @JwtToken String jwtToken, @PathVariable("movieName") String movieName)
    {
        Movie movieexist = movieService.getMovieByName(movieName);
        if(movieexist!=null) {
            Set<Tickets> ticketsList = ticketService.getAllTickets(movieexist.getMovieId());
            movieexist.setTicketsList(ticketsList);

       //     kafkaTemplate.send(topic.name(),movieexist);
            return new ResponseEntity<Movie>(movieexist,HttpStatus.OK);
        }
      //  kafkaTemplate.send(topic.name(),"Movie Record Missing. ");
        return new ResponseEntity<String>("Movie record missing", HttpStatus.NO_CONTENT);
    }

//    @PutMapping("/update")
//    public ResponseEntity<?> updateMovie(@RequestBody Movie movie)
//    {
//        if(movieService.updateMovie(movie))
//        {
//            return new ResponseEntity<String>("Movie got updated successfully!!", HttpStatus.OK);
//        }
//        return new ResponseEntity<String>("Movie updation failed", HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @GetMapping("/getAllBookedTickets")
    public ResponseEntity<?> getAllBookedTickets(@RequestHeader("Authorization") @JwtToken String jwtToken)
    {
        List<Tickets> bookedTickets = movieService.getAllBookedTickets();
        if(bookedTickets!=null)
        {
         //   kafkaTemplate.send(topic.name(),bookedTickets);
            return new ResponseEntity<>(bookedTickets,HttpStatus.OK);
        }
      //  kafkaTemplate.send(topic.name(),"Tickets Record is Empty !!  ");
        return new ResponseEntity<>("Tickets Record is Empty !!", HttpStatus.NO_CONTENT);
    }


    @GetMapping("/getAllBookedTickets/{movieName}")
    public ResponseEntity<?> getAllBookedTicketsByName(@RequestHeader("Authorization") @JwtToken String jwtToken,@PathVariable("movieName") String movieName)
    {
        List<Tickets> allBookedTickets = movieService.getAllBookedTicketsByName(movieName);
        if(allBookedTickets!=null) {
          //  kafkaTemplate.send(topic.name(),allBookedTickets);
            return new ResponseEntity<>(allBookedTickets,HttpStatus.OK);
        }
       // kafkaTemplate.send(topic.name(),"No Movie Exists !!  ");
        return new ResponseEntity<>("No Movie Exists !!", HttpStatus.NO_CONTENT);
    }
}
