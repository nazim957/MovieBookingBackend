package com.cognizant.moviebooking.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.util.Set;

@Entity
public class Movie {

    @Id
    private int movieId;
    private String movieName;
    private String theaterName;
    private int totalSeats;


    private int availableSeats;
//    private int bookedSeats;

   // @OneToMany(targetEntity = Tickets.class)
  //  private List<Tickets> ticketsList;
    @OneToMany(targetEntity = Tickets.class)
    private Set<Tickets> ticketsList;

    public Movie() {
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
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
//
//    public int getBookedSeats() {
//        return bookedSeats;
//    }
//
//    public void setBookedSeats(int bookedSeats) {
//        this.bookedSeats = bookedSeats;
//    }

    public Set<Tickets> getTicketsList() {
        return ticketsList;
    }

    public void setTicketsList(Set<Tickets> ticketsList) {
        this.ticketsList = ticketsList;
    }

    public Movie(int movieId, String movieName, String theaterName, int totalSeats) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.theaterName = theaterName;
        this.totalSeats = totalSeats;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", movieName='" + movieName + '\'' +
                ", theaterName='" + theaterName + '\'' +
                ", totalSeats=" + totalSeats +
//                ", availableSeats=" + availableSeats +
//                ", bookedSeats=" + bookedSeats +
                ", ticketsList=" + ticketsList +
                '}';
    }
}
