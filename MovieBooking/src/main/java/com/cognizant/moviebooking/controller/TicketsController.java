package com.cognizant.moviebooking.controller;

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

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/tickets")
public class TicketsController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private MovieService movieService;

//    @Autowired
//    private KafkaTemplate<String, Object> kafkaTemplate;
//
//    @Autowired
//    private NewTopic topic;

//    @GetMapping("/get")
//    public ResponseEntity<?> getAllTickets()
//    {
//        List<Tickets> allTickets = ticketService.getAllTickets();
//        return new ResponseEntity<List<Tickets>>(allTickets, HttpStatus.OK);
//    }

    @PostMapping("/add/{mid}")
    public ResponseEntity<?> addTickets(@RequestHeader("Authorization") @JwtToken String jwtToken, @PathVariable("mid") int movieId, @RequestBody Tickets tickets) {
        if(tickets.getBookedSeats()<1)
        {
            return ResponseHandler.generateResponse("Number of Tickets should be greater than one",HttpStatus.NOT_ACCEPTABLE);
        }

      else if (movieService.getMovieById(movieId) != null) {
            // tickets.setAvailableSeats(existMovie.getTotalSeats());
            if (ticketService.addTickets(tickets, movieId)) {
              //  return new ResponseEntity<Object>("Added", HttpStatus.OK);
               // kafkaTemplate.send(topic.name()," New Ticket Added : ");
                return ResponseHandler.generateResponse("Added", HttpStatus.OK) ;
            }
            else
         //   return new ResponseEntity<Object>("Sold", HttpStatus.OK);
              //  kafkaTemplate.send(topic.name()," All Tickets Sold : ");
               return ResponseHandler.generateResponse("Sold", HttpStatus.OK);
        }
        else
    //    return new ResponseEntity<Object>("Movie Id Not Exists", HttpStatus.NO_CONTENT);
          //  kafkaTemplate.send(topic.name()," Movie Id Not Exists : ");
            return ResponseHandler.generateResponse("Movie Id Not Exists", HttpStatus.NO_CONTENT);
    }
}