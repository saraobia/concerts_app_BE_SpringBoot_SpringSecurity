package com.project.service;

import com.project.model.Concert;
import com.project.service.interfaces.ConcertFunctions;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConcertService implements ConcertFunctions {


    @Override
    public List<Concert> viewAvailableConcertsFromNow() {
        return List.of();
    }

    @Override
    public Optional<Concert> getConcert(Integer idConcert) {
        return Optional.empty();
    }

    @Override
    public Optional<Concert> updateAvailablePlaceAfterPrenotation(Integer idConcert, int qta) {
        return Optional.empty();
    }
}
