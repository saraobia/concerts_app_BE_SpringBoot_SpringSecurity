package com.project.repository;

import com.project.model.Ticket;
import com.project.model.dto.TicketDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    @Query(value = "SELECT t.id, t.available_qta, t.id_concert, " +
            "c.id as concert_id, c.city, c.band, c.reply, c.available_place, c.date, c.price " +
            "FROM tickets t " +
            "JOIN concerts c ON t.id_concert = c.id " +
            "WHERE c.id = :idConcert",
            nativeQuery = true)
    Optional<Ticket> findByIdConcert(@Param("idConcert") Integer idConcert);
}

