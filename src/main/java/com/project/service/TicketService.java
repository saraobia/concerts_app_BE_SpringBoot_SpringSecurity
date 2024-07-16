package com.project.service;

import com.project.exception.ConcertException;
import com.project.exception.TicketException;
import com.project.model.Concert;
import com.project.model.Ticket;
import com.project.model.dto.ConcertDTO;
import com.project.model.dto.TicketDTO;
import com.project.model.enums.ErrorCode;
import com.project.repository.TicketRepository;
import com.project.response.ErrorResponse;
import com.project.service.interfaces.TicketFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketService implements TicketFunctions {

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public TicketDTO updatedTicketsQuantity(Integer idConcert, int qta) {
        Ticket ticket = ticketRepository.findByIdConcert(idConcert)
                .orElseThrow(() -> new TicketException(
                        new ErrorResponse(ErrorCode.TNF, "Ticket not found with idConcert: " + idConcert)));

        if (ticket.getAvailableQta() < qta) {
            throw new TicketException(
                    new ErrorResponse(ErrorCode.ITQ, "Invalid ticket quantity for concert: " + idConcert));
        }

        ticket.setAvailableQta(ticket.getAvailableQta() - qta);
        ticketRepository.save(ticket);

        return convertToTicketDTO(ticket);
    }


    private TicketDTO convertToTicketDTO(Ticket ticket) {
        return TicketDTO.builder()
                .id(ticket.getId())
                .availableQta(ticket.getAvailableQta())
                .build();
    }

    /*
    @Override
    public boolean updatedTicketsQuantity(Integer idConcert, int qta) {
        return false;
    }*/
}


