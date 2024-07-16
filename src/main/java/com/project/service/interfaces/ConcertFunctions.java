package com.project.service.interfaces;

import com.project.model.Concert;
import com.project.model.dto.ConcertDTO;

import java.util.List;
import java.util.Optional;

public interface ConcertFunctions {
    List<ConcertDTO> viewAvailableConcertsFromNow();
    ConcertDTO getConcert(Integer idConcert);
    boolean updateAvailablePlaceAfterPrenotation(Integer idConcert, int qta);
}
