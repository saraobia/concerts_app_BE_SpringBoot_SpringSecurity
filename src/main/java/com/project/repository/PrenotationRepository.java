package com.project.repository;

import com.project.model.Prenotation;
import com.project.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrenotationRepository extends JpaRepository<Prenotation, Integer> {

    @Query("SELECT p FROM Prenotation p WHERE p.user.id = :idUser AND p.ticket.concert.id = :idConcert")
    Optional<Prenotation> findByUserIdAndConcertId(@Param("idUser") Integer idUser, @Param("idConcert") Integer idConcert);

    @Query("SELECT p FROM Prenotation p WHERE p.user.id = :idUser")
    List<Prenotation> findAllByUserId(@Param("idUser") Integer idUser);
}
