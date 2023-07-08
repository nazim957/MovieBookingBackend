package com.cognizant.moviebooking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

@Entity
public class Tickets {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int transactionId;
    private String movieName;
    private String theaterName;

  //  @JsonIgnore comment on 25/05
    private int totalSeats;

    //@JsonIgnore comment on 25/05
    private int availableSeats;
    private int bookedSeats;
    private int movie_id_fk;

    public Tickets(int transactionId, String movieName, String theaterName, int bookedSeats, int movie_id_fk) {
        this.transactionId = transactionId;
        this.movieName = movieName;
        this.theaterName = theaterName;
        this.bookedSeats = bookedSeats;
        this.movie_id_fk = movie_id_fk;
    }

    public Tickets() {
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public int getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(int bookedSeats) {
        this.bookedSeats = bookedSeats;
    }

    public int getMovie_id_fk() {
        return movie_id_fk;
    }

    public void setMovie_id_fk(int movie_id_fk) {
        this.movie_id_fk = movie_id_fk;
    }
}
