package com.project.service;

import com.project.exception.ConcertException;
import com.project.exception.PrenotationException;
import com.project.exception.TicketException;
import com.project.exception.UserException;
import com.project.model.Concert;
import com.project.model.Prenotation;
import com.project.model.Ticket;
import com.project.model.User;
import com.project.model.dto.ConcertDTO;
import com.project.model.dto.PrenotationDTO;
import com.project.model.dto.PrenotationWithTicketsDTO;
import com.project.model.dto.TicketDTO;
import com.project.model.enums.ErrorCode;
import com.project.model.enums.PaymentType;
import com.project.repository.ConcertRepository;
import com.project.repository.PrenotationRepository;
import com.project.repository.TicketRepository;
import com.project.repository.UserRepository;
import com.project.response.ErrorResponse;
import com.project.service.interfaces.PrenotationFunctions;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PrenotationService implements PrenotationFunctions {

    @Autowired
    private ConcertRepository concertRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PrenotationRepository prenotationRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private ConcertService concertService;

    @Override
    public boolean createAndConfirmPrenotation(Integer idUser, Integer idConcert, int qta, PaymentType paymentType) {
        // VERIFY CONCERT
        Concert concert = concertRepository.findById(idConcert)
                .orElseThrow(() -> new ConcertException(
                        new ErrorResponse(ErrorCode.CNF, "Concert not found with id: " + idConcert)));

        // VERIFY USER
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new UserException(
                        new ErrorResponse(ErrorCode.EUN, "User not found with id: " + idUser)));

        // FIND OR CREATE PRENOTATION
        Prenotation prenotation = prenotationRepository.findByUserIdAndConcertId(idUser, idConcert)
                .orElseGet(() -> {
                    Prenotation newPrenotation = new Prenotation();
                    newPrenotation.setUser(user);
                    newPrenotation.setQta(0);
                    newPrenotation.setPaymentType(paymentType);
                    return newPrenotation;
                });

        // UPDATE PRENOTATION
        prenotation.setQta(prenotation.getQta() + qta);
        prenotation.setTotalPrice(concert.getPrice().multiply(BigDecimal.valueOf(prenotation.getQta())));

        // FIND TICKET REFERENCE AND UPDATE QTA
        Ticket ticket = ticketRepository.findByIdConcert(idConcert)
                .orElseThrow(() -> new TicketException(
                        new ErrorResponse(ErrorCode.TNF, "Ticket not found for concert with id: " + idConcert)));

        if (ticket.getAvailableQta() < qta) {
            throw new TicketException(new ErrorResponse(ErrorCode.ITQ, "Insufficient tickets available"));
        }

        ticket.setAvailableQta(ticket.getAvailableQta() - qta);
        ticketRepository.save(ticket);

        prenotation.setTicket(ticket);
        prenotationRepository.save(prenotation);

        return true;
    }

    @Override
    public List<PrenotationWithTicketsDTO> viewClientPrenotations(Integer idUser) {
        List<Prenotation> prenotations = prenotationRepository.findAllByUserId(idUser);
        if (prenotations.isEmpty())
            throw new PrenotationException(
                    new ErrorResponse(ErrorCode.NUP, "No prenotation for user with id: " + idUser));

        return prenotations.stream()
                .map(this::convertToPrenotationWithTicketsDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public boolean addTicketsToExistingPrenotation(Integer idPrenotation, int qta) {
      Prenotation prenotation = prenotationRepository.findById(idPrenotation)
                                .orElseThrow(() -> new PrenotationException(
                                                  new ErrorResponse(ErrorCode.PNF, "prenotation not found with id: " + idPrenotation)));
        int newQta = prenotation.getQta() + qta;
        prenotation.setQta(newQta);
        prenotationRepository.save(prenotation);

        return true;
    }

    @Override
    public PrenotationDTO getPrenotation(Integer idPrenotation) {
        Prenotation prenotation = prenotationRepository.findById(idPrenotation)
                .orElseThrow(() -> new PrenotationException(
                        new ErrorResponse(ErrorCode.PNF, "prenotation not found with id: " + idPrenotation)));

        return convertToPrenotationDTO(prenotation);
    }

    private PrenotationDTO convertToPrenotationDTO(Prenotation prenotation) {
        return PrenotationDTO.builder()
                .id(prenotation.getId())
                .paymentType(prenotation.getPaymentType())
                .qta(prenotation.getQta())
                .totalPrice(prenotation.getTotalPrice())
                .build();
    }

    private PrenotationWithTicketsDTO convertToPrenotationWithTicketsDTO(Prenotation prenotation) {
        return PrenotationWithTicketsDTO.builder()
                .id(prenotation.getId())
                .paymentType(prenotation.getPaymentType())
                .qta(prenotation.getQta())
                .totalPrice(prenotation.getTotalPrice())
                .ticket(convertToTicketDTO(prenotation.getTicket()))
                .build();
    }

    private TicketDTO convertToTicketDTO(Ticket ticket) {
        return TicketDTO.builder()
                .id(ticket.getId())
                .concert(convertToConcertDTO(ticket.getConcert()))
                .availableQta(ticket.getAvailableQta())
                .build();
    }


    private ConcertDTO convertToConcertDTO(Concert concert) {
        return concertService.convertToConcertDTO(concert);
    }
}
