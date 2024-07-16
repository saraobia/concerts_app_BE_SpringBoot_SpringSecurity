package com.project.service.interfaces;

import com.project.model.Prenotation;
import com.project.model.dto.PrenotationDTO;
import com.project.model.dto.PrenotationWithTicketsDTO;
import com.project.model.enums.PaymentType;

import java.util.List;
import java.util.Optional;

public interface PrenotationFunctions {
    boolean createAndConfirmPrenotation(Integer idUser, Integer idConcert,  int qta, PaymentType paymentType);
    List<PrenotationWithTicketsDTO> viewClientPrenotations(Integer idUser);
    boolean addTicketsToExistingPrenotation(Integer idPrenotation, int qta);
    PrenotationDTO getPrenotation(Integer idPrenotation);
}
