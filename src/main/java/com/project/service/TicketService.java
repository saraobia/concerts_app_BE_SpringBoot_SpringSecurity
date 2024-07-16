package com.project.service;

import com.project.model.Concert;
import com.project.model.Ticket;
import com.project.model.dto.ConcertDTO;
import com.project.model.dto.TicketDTO;
import com.project.service.interfaces.TicketFunctions;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketService implements TicketFunctions {
    @Override
    public TicketDTO updatedTicketsQuantity(String idConcert, int qta) {
        return null;
    }

    private TicketDTO convertToTicketDTO(Ticket ticket) {
        return TicketDTO.builder()
                .id(ticket.getId())
                .availableQta(ticket.getAvailableQta())
                .build();
    }
}
