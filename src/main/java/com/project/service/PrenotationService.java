package com.project.service;

import com.project.exception.ConcertException;
import com.project.exception.PrenotationException;
import com.project.exception.TicketException;
import com.project.exception.UserException;
import com.project.model.Concert;
import com.project.model.Prenotation;
import com.project.model.Ticket;
import com.project.model.User;
import com.project.model.dto.PrenotationDTO;
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

    @Override
    public boolean createAndConfirmPrenotation(Integer idUser, Integer idConcert, int qta, PaymentType paymentType) {
       //VERIFY CONCERT
        Concert concert = concertRepository.findById(idConcert)
                        .orElseThrow(()-> new ConcertException(
                        new ErrorResponse(ErrorCode.CNF, "Concert not found with id: " + idConcert)));

        //VERIFY USER
        User user = userRepository.findById(idUser)
                    .orElseThrow(()-> new UserException(
                    new ErrorResponse(ErrorCode.EUN, "User not found with id: " + idUser)));

        //FIND TICKET REFERENCE AND UPDATE QTA
        TicketDTO ticketDTO = ticketService.updatedTicketsQuantity(idConcert, qta);
        Integer idTicket = ticketDTO.getId();

        Ticket ticket = ticketRepository.findById(idTicket)
                .orElseThrow(()-> new TicketException(
                new ErrorResponse(ErrorCode.TNF, "Ticket not found with id: " + idTicket)));


        BigDecimal convertQta = new BigDecimal(qta);
        Optional<Prenotation> optPrenotation = prenotationRepository.findByUserIdAndTicketId(idConcert, idTicket);
        if(optPrenotation.isPresent()) {
            Prenotation existingPrenotation = optPrenotation.get();
            addTicketsToExistingPrenotation(existingPrenotation.getId(), qta);
            existingPrenotation.setTotalPrice(existingPrenotation.getTotalPrice().add(concert.getPrice().multiply(convertQta)));
            prenotationRepository.save(existingPrenotation);
        }

        Prenotation prenotation = new Prenotation();
        prenotation.setUser(user);
        prenotation.setQta(qta);
        prenotation.setTicket(ticket);
        prenotation.setTotalPrice(concert.getPrice().multiply(convertQta));

        prenotationRepository.save(prenotation);

        return true;
    }

    @Override
    public List<PrenotationDTO> viewClientPrenotations(Integer idUser) {
        List<Prenotation> prenotations = prenotationRepository.findAllByUserId(idUser);
        if (prenotations.isEmpty())
            throw new PrenotationException(
                    new ErrorResponse(ErrorCode.NUP, "No prenotation for user with id: " + idUser));

        return prenotations.stream()
                            .map(this::convertToPrenotationDTO)
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
}
