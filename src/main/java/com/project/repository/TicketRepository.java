package com.project.repository;

import com.project.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
/*
    @Query("SELECT t FROM Ticket t WHERE t.concert.idConcert = :idConcert")
    Optional<Ticket> findByIdConcert(@Param("idConcert") Integer idConcert);
*/
}
