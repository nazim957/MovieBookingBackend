//package com.cognizant.moviebooking.controller;
//
//import com.cognizant.moviebooking.exceptions.MovieIdAlreadyExistsExceptions;
//import com.cognizant.moviebooking.exceptions.MovieIdNotExistsExceptions;
//import com.cognizant.moviebooking.model.Movie;
//import com.cognizant.moviebooking.model.Tickets;
//import com.cognizant.moviebooking.service.MovieService;
//import com.cognizant.moviebooking.service.TicketService;
//import org.apache.kafka.clients.admin.NewTopic;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Set;
//
//
//@RestController
//@RequestMapping("/publisher")
//public class PubController {
//
//    @Autowired
//    private MovieService movieService;
//
//    @Autowired
//    private TicketService ticketService;
//
//
//    @Autowired
//    private KafkaTemplate<String, Object> kafkaTemplate;
//
//    @Autowired
//    private NewTopic topic;
//
//    @PostMapping("/addMovie")
//    public void produceMovie(@RequestBody Movie movie) throws MovieIdAlreadyExistsExceptions {
//        if (movieService.addMovie(movie) != null) {
//            System.out.println("Inside");
//            kafkaTemplate.send(topic.name(), movie);
//
//        } else {
//            throw new MovieIdAlreadyExistsExceptions();
//        }
//    }
//
//    @PostMapping("/addTicket/{mid}")
//    public void produceTickets(@PathVariable("mid") int movieId, @RequestBody Tickets tickets) {
//        if (movieService.getMovieById(movieId) != null) {
//            if (ticketService.addTickets(tickets, movieId)) {
//                kafkaTemplate.send(topic.name(),"Ticket Added Sucessfully");
//                return;
//            }
//            kafkaTemplate.send(topic.name(),"Sorry Tickets has been Sold Out!");
//            return;
//        }
//        else {
//            kafkaTemplate.send(topic.name(), "Movie that you search did not Exists");
//            return;
//        }
//    }
//
//    @GetMapping("/getAllMovies")
//    public void consumeMovie() {
//        List<Movie> movieList = movieService.getAllMovies();
//        if (movieList != null) {
//            for (Movie m : movieList) {
//                Set<Tickets> ticketsList = ticketService.getAllTickets(m.getMovieId());
//                m.setTicketsList(ticketsList);
//            }
//            kafkaTemplate.send(topic.name(), movieList);
//
//        }
//    }
//
//    @DeleteMapping("/deleteMovieById/{mid}")
//    public void deleteMovie(@PathVariable("mid") int mid) throws MovieIdNotExistsExceptions {
//        if (movieService.deleteMovie(mid) && ticketService.deleteTickets(mid)) {
//            kafkaTemplate.send(topic.name(), "Movie Deleted successfully. ");
//        }
//        else {
//            kafkaTemplate.send(topic.name(), "Failed to Delete Movie!! ");
//        }
//    }
//
//}
