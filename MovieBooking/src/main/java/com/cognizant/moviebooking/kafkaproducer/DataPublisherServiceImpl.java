//package com.cognizant.moviebooking.kafkaproducer;
//
//import com.cognizant.moviebooking.model.Movie;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class DataPublisherServiceImpl {
//	public static final String topic = "moviebookingapplication";
//
//	@Autowired
//	private KafkaTemplate<String, String> temp; // for sending string messages
//
//	public KafkaTemplate<String, String> getTemp() {
//		return temp;
//	}
//
//	public void setTemp(String message) {
//		this.temp.send(topic, message);
//	}
//
//	@Autowired
//	private KafkaTemplate<String, Movie> kafkaTemplate; // for sending Movie objects
//
//	public void setKafkaTemplate(Movie movie) {
//		this.kafkaTemplate.send(topic, String.valueOf(movie.getMovieId()), movie);
//	}
//
//
//	public static String getTopic() {
//		return topic;
//	}
//}
