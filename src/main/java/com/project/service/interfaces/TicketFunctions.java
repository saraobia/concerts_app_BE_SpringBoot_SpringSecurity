package com.project.service.interfaces;

import com.project.model.Ticket;

import java.util.Optional;

public interface TicketFunctions {
    //AFTER BUY
    Optional<Ticket> updatedTicketsQuantity(String idConcert, int qta);
}
