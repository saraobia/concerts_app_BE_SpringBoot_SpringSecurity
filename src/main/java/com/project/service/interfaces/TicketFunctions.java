package com.project.service.interfaces;

import com.project.model.Ticket;
import com.project.model.dto.TicketDTO;

import java.util.Optional;

public interface TicketFunctions {
    TicketDTO updatedTicketsQuantity(String idConcert, int qta);
}
