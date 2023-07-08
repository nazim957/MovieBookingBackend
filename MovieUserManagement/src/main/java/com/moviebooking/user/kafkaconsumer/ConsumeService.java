//package com.moviebooking.user.kafkaconsumer;
//
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//
//@Service
//public class ConsumeService
//{
//	@KafkaListener(topics="moviebookingapplication", groupId="mymoviegroup")
//	public void consumeFromTopic(String message)
//	{
//		System.out.println("Consumer message: "+ message);
//	}
//
////	@KafkaListener(topics="movie", groupId="mygroup")
////	public void consumeMovieFromTopic(String message)
////	{
////		System.out.println("Consumer message: "+ message);
////	}
//
//}
