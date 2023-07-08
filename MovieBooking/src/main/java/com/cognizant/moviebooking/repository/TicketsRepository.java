package com.cognizant.moviebooking.repository;

import com.cognizant.moviebooking.model.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public interface TicketsRepository extends JpaRepository<Tickets, Integer> {

    @Query(value = "select t from Tickets t where t.movie_id_fk= :movieId")
    public Set<Tickets> getTicketsList(int movieId);


    @Modifying
    @Query(value="delete from Tickets where movie_id_fk= :movieId")
    public void deleteTicketsData(int movieId);

    public List<Tickets> findByMovieName(String movieName);

//    @Modifying
//    @Query(value = "update from Tickets where movie_id_fk= :movieId")
//    public void updateTickets(int movieId);
}
