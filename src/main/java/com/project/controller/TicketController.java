package com.project.controller;

import com.project.model.dto.UserDTO;
import com.project.response.SuccessResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @GetMapping("/")
    public ResponseEntity<SuccessResponse<UserDTO>> getTickets(HttpServletRequest request){
        return new ResponseEntity<>(HttpStatus.OK);
       // return new ResponseEntity<>(new SuccessResponse<>(customUserDetailsService.loadUser(request)), HttpStatus.OK);
    }
}
