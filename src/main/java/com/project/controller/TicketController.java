package com.project.controller;

import com.project.model.dto.UserDTO;
import com.project.response.SuccessResponse;
import com.project.service.TicketService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    /*
    @Autowired
    private TicketService ticketService;

    @PatchMapping("/")
    public ResponseEntity<SuccessResponse<Boolean>> updateTicketsQta(@PathVariable Integer idConcert,
                                                                     @PathVariable int qta,
                                                                     HttpServletRequest request){
        boolean updatedSuccessfully = ticketService.updatedTicketsQuantity(idConcert, qta);
        return updatedSuccessfully
                ? new ResponseEntity<>(new SuccessResponse<>(updatedSuccessfully), HttpStatus.OK)
                : new ResponseEntity<>(new SuccessResponse<>(updatedSuccessfully), HttpStatus.BAD_REQUEST);
    }*/
}
