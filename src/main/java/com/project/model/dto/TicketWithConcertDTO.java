package com.project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketWithConcertDTO {

    private Integer id;
    private ConcertDTO concert;
    private int availableQta;
}
