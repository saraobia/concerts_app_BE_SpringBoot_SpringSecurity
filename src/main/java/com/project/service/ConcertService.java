package com.project.service;

import com.project.model.Concert;
import com.project.model.dto.ConcertDTO;
import com.project.repository.ConcertRepository;
import com.project.service.interfaces.ConcertFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConcertService implements ConcertFunctions {

    @Autowired
    private ConcertRepository concertRepository;

    @Override
    public List<ConcertDTO> viewAvailableConcertsFromNow() {
        List<Concert> concerts = concertRepository.findAllFromNow();
        return concerts.stream()
                .map(this::convertToConcertDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Concert> getConcert(Integer idConcert) {
        return Optional.empty();
    }

    @Override
    public Optional<Concert> updateAvailablePlaceAfterPrenotation(Integer idConcert, int qta) {
        return Optional.empty();
    }

    private ConcertDTO convertToConcertDTO(Concert concert) {
        return ConcertDTO.builder()
                .id(concert.getId())
                .city(concert.getCity())
                .band(concert.getBand())
                .reply(concert.getReply())
                .availablePlace(concert.getAvailablePlace())
                .date(concert.getDate())
                .price(concert.getPrice())
                .build();
    }
}
