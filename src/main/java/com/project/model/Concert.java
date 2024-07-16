package com.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "concerts")
public class Concert implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "city")
    private String city;

    @Column(name = "band")
    private String band;

    @Column(name = "reply")
    private String reply;

    @Column(name = "available_place")
    private int availablePlace;

    @Column(name = "date")
    private LocalDate date;

    @Column (name = "price")
    private BigDecimal price;

    // One-to-many relationship with Ticket entity: a flight can have multiple tickets
   /* @OneToMany(mappedBy = "concert", fetch = FetchType.LAZY)
    private List<Ticket> tickets;*/

    @OneToOne(mappedBy = "concert", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Ticket ticket;
}
