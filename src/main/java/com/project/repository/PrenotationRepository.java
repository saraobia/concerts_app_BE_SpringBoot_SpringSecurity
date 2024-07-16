package com.project.repository;

import com.project.model.Prenotation;
import com.project.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrenotationRepository extends JpaRepository<Prenotation, Integer> {



}
