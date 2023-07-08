//package com.cognizant.moviebooking.kafkaproducer;
//
//import com.cognizant.moviebooking.exceptions.MovieIdAlreadyExistsExceptions;
//import com.cognizant.moviebooking.model.Movie;
//import com.cognizant.moviebooking.model.Tickets;
//import com.cognizant.moviebooking.service.MovieService;
//import com.cognizant.moviebooking.service.TicketService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Set;
//
//@RestController
//@RequestMapping("/publisher")
//public class PubController {
//	@Autowired
//	DataPublisherServiceImpl dp;
//
//	@Autowired
//	private MovieService movieService;
//
//	@Autowired
//	private TicketService ticketService;
//
//	@PostMapping("/produce")
//	public void publishMessage(@RequestParam("msg") String msg) {
//		dp.setTemp(msg);
//	}
//
//	@PostMapping("/addMovie")
//	public void publishMsg(@RequestBody Movie movie) throws MovieIdAlreadyExistsExceptions {
//		dp.setKafkaTemplate(movie);
//	}
//
//	@PostMapping("/addTicket/{mid}")
//	public void addTickets(@PathVariable("mid") int movieId, @RequestBody Tickets tickets) {
//		if (movieService.getMovieById(movieId) != null) {
//			if (ticketService.addTickets(tickets, movieId)) {
//				dp.setTemp("Ticket Added Sucessfully");
//				return;
//			}
//			dp.setTemp("Sorry Tickets has been Sold Out!");
//			return;
//		} else {
//			dp.setTemp("Movie that you search did not Exists");
//			return;
//		}
//	}
//
//	@GetMapping("/allmovies")
//	public void consumeMsg() {
//		List<Movie> movieList = movieService.getAllMovies();
//
//		for (Movie m : movieList) {
//			Set<Tickets> ticketsList = ticketService.getAllTickets(m.getMovieId());
//			m.setTicketsList(ticketsList);
//			dp.setKafkaTemplate(m);
//		}
//	}
//
//}
