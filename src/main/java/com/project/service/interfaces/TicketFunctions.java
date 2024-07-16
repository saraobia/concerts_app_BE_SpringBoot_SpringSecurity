package com.project.service.interfaces;

import com.project.model.Ticket;
import com.project.model.dto.TicketDTO;

import java.util.Optional;

public interface TicketFunctions {
    boolean updatedTicketsQuantity(Integer idConcert, int qta);
}
