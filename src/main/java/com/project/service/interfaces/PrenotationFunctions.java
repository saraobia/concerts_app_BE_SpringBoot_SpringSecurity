package com.project.service.interfaces;

import com.project.model.Prenotation;
import com.project.model.enums.PaymentType;

import java.util.List;
import java.util.Optional;

public interface PrenotationFunctions {
    boolean createAndConfirmPrenotation(Integer idUser, Integer idConcert,  int qta, PaymentType paymentType);
    List<Prenotation> viewClientPrenotations(String idUser);
    boolean addTicketsToExistingPrenotation(String idUser, int qta);
    Optional<Prenotation> getPrenotation(String idPrenotation);
}
