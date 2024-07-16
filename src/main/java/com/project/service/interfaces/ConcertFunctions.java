package com.project.service.interfaces;

import com.project.model.Concert;

import java.util.List;
import java.util.Optional;
t
public interface ConcertFunctions {
    List<Concert> viewAvailableConcertsFromNow();
    Optional<Concert> getConcert(Integer idConcert);
    Optional<Concert> updateAvailablePlaceAfterPrenotation(Integer idConcert, int qta);
}
