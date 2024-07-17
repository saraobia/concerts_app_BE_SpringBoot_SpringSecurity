package com.project.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.model.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrenotationWithTicketsDTO {

    private Integer id;
    private int qta;
    private PaymentType paymentType;

    @JsonProperty("totalPrice")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal totalPrice;
    private TicketDTO ticket;

}
