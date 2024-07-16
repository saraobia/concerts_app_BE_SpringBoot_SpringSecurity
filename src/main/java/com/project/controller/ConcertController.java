package com.project.controller;

import com.project.model.dto.ConcertDTO;
import com.project.model.dto.UserDTO;
import com.project.response.SuccessResponse;
import com.project.service.ConcertService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
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

    @GetMapping("/detail/{idConcert}")
    public ResponseEntity<SuccessResponse<ConcertDTO>> getConcertDetail(@PathVariable Integer idConcert,
                                                                        HttpServletRequest request) {
        ConcertDTO concertDTO = concertService.getConcert(idConcert);
        return new ResponseEntity<>(new SuccessResponse<>(concertDTO), HttpStatus.OK);
    }

    @PatchMapping("/detail/place/{idConcert}/{qta}")
    public ResponseEntity<SuccessResponse<Boolean>> updatePlace(@PathVariable Integer idConcert,
                                                                @PathVariable int qta,
                                                                HttpServletRequest request) {
        boolean updatedSuccessfully = concertService.updateAvailablePlaceAfterPrenotation(idConcert, qta);
        return updatedSuccessfully
               ? new ResponseEntity<>(new SuccessResponse<>(updatedSuccessfully), HttpStatus.OK)
               : new ResponseEntity<>(new SuccessResponse<>(updatedSuccessfully), HttpStatus.BAD_REQUEST);
    }

    // Endpoint di test
    @GetMapping("/test")
    public ConcertDTO testBigDecimal() {
        ConcertDTO dto = ConcertDTO.builder()
                .id(10)
                .city("Test City")
                .band("Test Band")
                .reply("Test Reply")
                .availablePlace(100)
                .date(LocalDate.now())
                .price(new BigDecimal("123.45"))
                .build();
        return dto;
    }
}
