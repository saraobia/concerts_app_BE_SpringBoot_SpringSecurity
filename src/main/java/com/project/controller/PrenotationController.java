package com.project.controller;

import com.project.model.PaymentRequest;
import com.project.model.dto.PrenotationDTO;
import com.project.model.dto.PrenotationWithTicketsDTO;
import com.project.model.dto.UserDTO;
import com.project.response.SuccessResponse;
import com.project.service.PrenotationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prenotations")
public class PrenotationController {

    @Autowired
    private PrenotationService prenotationService;

    @GetMapping("/user/{idUser}")
    public ResponseEntity<SuccessResponse<List<PrenotationWithTicketsDTO>>> getPrenotations(@PathVariable Integer idUser,
                                                                                            HttpServletRequest request){
        return new ResponseEntity<>(new SuccessResponse<>(prenotationService.viewClientPrenotations(idUser)), HttpStatus.OK);
    }

    @PostMapping("/user/{idUser}/{idConcert}/{qta}")
    public ResponseEntity<SuccessResponse<Boolean>> createAndConfirmPrenotation(@PathVariable Integer idUser,
                                                                               @PathVariable Integer idConcert,
                                                                               @PathVariable int qta,
                                                                               @RequestBody PaymentRequest paymentRequest,
                                                                               HttpServletRequest request){

        return new ResponseEntity<>(new SuccessResponse<>(prenotationService.createAndConfirmPrenotation(idUser, idConcert, qta, paymentRequest.getPaymentType())), HttpStatus.OK);
    }

    @GetMapping("detail/{idPrenotation}")
    public ResponseEntity<SuccessResponse<PrenotationDTO>> getPrenotation(@PathVariable Integer idPrenotation,
                                                                          HttpServletRequest request){
        return new ResponseEntity<>(new SuccessResponse<>(prenotationService.getPrenotation(idPrenotation)), HttpStatus.OK);
    }

}
