package com.project.controller;

import com.project.model.dto.ConcertDTO;
import com.project.model.dto.UserDTO;
import com.project.response.SuccessResponse;
import com.project.service.ConcertService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/concerts")
public class ConcertController {

    @Autowired
    private ConcertService concertService;

    @GetMapping("/")
    public ResponseEntity<SuccessResponse<List<ConcertDTO>>> getConcerts(HttpServletRequest request){
        return new ResponseEntity<>(new SuccessResponse<>(concertService.viewAvailableConcertsFromNow()), HttpStatus.OK);
    }
}
