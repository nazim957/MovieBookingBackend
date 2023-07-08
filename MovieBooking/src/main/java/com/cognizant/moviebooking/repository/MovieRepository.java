package com.cognizant.moviebooking.repository;

import com.cognizant.moviebooking.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    public Movie findByMovieName(String movieName);

}
