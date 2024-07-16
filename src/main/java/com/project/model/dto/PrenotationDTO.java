package com.project.model.dto;

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
public class PrenotationDTO {

    private Integer id;
    private int qta;
    private PaymentType paymentType;
    private BigDecimal totalPrice;
}
