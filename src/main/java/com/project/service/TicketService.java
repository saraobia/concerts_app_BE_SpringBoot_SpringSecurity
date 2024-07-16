package com.project.service;

import com.project.model.Ticket;
import com.project.service.interfaces.TicketFunctions;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketService implements TicketFunctions {
    @Override
    public Optional<Ticket> updatedTicketsQuantity(String idConcert, int qta) {
        return Optional.empty();
    }
}
