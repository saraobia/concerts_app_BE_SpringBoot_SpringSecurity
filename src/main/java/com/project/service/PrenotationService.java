package com.project.service;

import com.project.model.Prenotation;
import com.project.model.enums.PaymentType;
import com.project.service.interfaces.PrenotationFunctions;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrenotationService implements PrenotationFunctions {

    @Override
    public boolean createAndConfirmPrenotation(Integer idUser, Integer idConcert, int qta, PaymentType paymentType) {
        return false;
    }

    @Override
    public List<Prenotation> viewClientPrenotations(String idUser) {
        return List.of();
    }

    @Override
    public boolean addTicketsToExistingPrenotation(String idUser, int qta) {
        return false;
    }

    @Override
    public Optional<Prenotation> getPrenotation(String idPrenotation) {
        return Optional.empty();
    }
}
