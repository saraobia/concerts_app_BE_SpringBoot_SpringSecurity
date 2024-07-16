package com.project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConcertWithTicketsDTO {
    private ConcertDTO concert;
    private List<TicketDTO> tickets;
}