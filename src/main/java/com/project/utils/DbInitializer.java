package com.project.utils;

import com.project.model.*;
import com.project.model.enums.*;
import com.project.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class DbInitializer implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ConcertRepository concertRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    PrenotationRepository prenotationRepository;

    @Override
    public void run(String... args) throws Exception {
        initializeRolesAndUsers();
        initializeConcerts();
        initializeTickets();
        initializePrenotations();
    }

    private void initializeRolesAndUsers() {
        if (!roleRepository.existsByRoleCode(RoleCode.ROLE_USER)) {
            roleRepository.save(Role.builder().role(RoleCode.ROLE_USER).build());
        }
        if (!roleRepository.existsByRoleCode(RoleCode.ROLE_ADMIN)) {
            roleRepository.save(Role.builder().role(RoleCode.ROLE_ADMIN).build());
        }
        Role roleAdmin = roleRepository.findByRoleCode(RoleCode.ROLE_ADMIN).orElseThrow();
        Role roleUser = roleRepository.findByRoleCode(RoleCode.ROLE_USER).orElseThrow();

        if(userRepository.findByEmail("admin@mail.com").isEmpty()) {
            userRepository.save(User.builder()
                    .email("admin@mail.com")
                    .password(passwordEncoder.encode("admin1234"))
                    .name("Admin")
                    .surname("User")
                    .roles(List.of(roleAdmin))
                    .build());
        }

        if(userRepository.findByEmail("user@mail.com").isEmpty()) {
            userRepository.save(User.builder()
                    .email("user@mail.com")
                    .password(passwordEncoder.encode("user1234"))
                    .name("Regular")
                    .surname("User")
                    .roles(List.of(roleUser))
                    .build());
        }

        if(userRepository.findByEmail("empty@mail.com").isEmpty()) {
            userRepository.save(User.builder()
                    .email("empty@mail.com")
                    .password(passwordEncoder.encode("empty1234"))
                    .name("Empty")
                    .surname("User")
                    .roles(List.of(roleUser))
                    .build());
        }
    }


    private void initializeConcerts() {
        if (concertRepository.count() == 0) {
            concertRepository.saveAll(List.of(
                    Concert.builder()
                            .city("New York")
                            .band("The Rolling Stones")
                            .reply("Madison Square Garden")
                            .availablePlace(1000)
                            .date(LocalDate.of(2024, 8, 15))
                            .price(new BigDecimal("150.00"))
                            .build(),
                    Concert.builder()
                            .city("Los Angeles")
                            .band("Coldplay")
                            .reply("SoFi Stadium")
                            .availablePlace(1500)
                            .date(LocalDate.of(2024, 9, 20))
                            .price(new BigDecimal("120.00"))
                            .build(),
                    Concert.builder()
                            .city("Chicago")
                            .band("Imagine Dragons")
                            .reply("United Center")
                            .availablePlace(1200)
                            .date(LocalDate.of(2024, 7, 25))
                            .price(new BigDecimal("110.00"))
                            .build(),
                    Concert.builder()
                            .city("Houston")
                            .band("Metallica")
                            .reply("Toyota Center")
                            .availablePlace(1300)
                            .date(LocalDate.of(2024, 10, 5))
                            .price(new BigDecimal("140.00"))
                            .build(),
                    Concert.builder()
                            .city("Phoenix")
                            .band("U2")
                            .reply("State Farm Stadium")
                            .availablePlace(900)
                            .date(LocalDate.of(2024, 11, 15))
                            .price(new BigDecimal("160.00"))
                            .build(),
                    Concert.builder()
                            .city("Philadelphia")
                            .band("Maroon 5")
                            .reply("Wells Fargo Center")
                            .availablePlace(1100)
                            .date(LocalDate.of(2024, 6, 18))
                            .price(new BigDecimal("125.00"))
                            .build(),
                    Concert.builder()
                            .city("San Antonio")
                            .band("Linkin Park")
                            .reply("AT&T Center")
                            .availablePlace(1400)
                            .date(LocalDate.of(2024, 12, 2))
                            .price(new BigDecimal("135.00"))
                            .build(),
                    Concert.builder()
                            .city("San Diego")
                            .band("The Beatles Tribute")
                            .reply("Pechanga Arena")
                            .availablePlace(800)
                            .date(LocalDate.of(2024, 5, 21))
                            .price(new BigDecimal("100.00"))
                            .build(),
                    Concert.builder()
                            .city("Dallas")
                            .band("Queen")
                            .reply("American Airlines Center")
                            .availablePlace(1500)
                            .date(LocalDate.of(2024, 4, 17))
                            .price(new BigDecimal("150.00"))
                            .build(),
                    Concert.builder()
                            .city("San Jose")
                            .band("The Who")
                            .reply("SAP Center")
                            .availablePlace(700)
                            .date(LocalDate.of(2024, 3, 25))
                            .price(new BigDecimal("130.00"))
                            .build(),
                    Concert.builder()
                            .city("Austin")
                            .band("Red Hot Chili Peppers")
                            .reply("Frank Erwin Center")
                            .availablePlace(1200)
                            .date(LocalDate.of(2024, 2, 14))
                            .price(new BigDecimal("140.00"))
                            .build(),
                    Concert.builder()
                            .city("Jacksonville")
                            .band("Foo Fighters")
                            .reply("VyStar Veterans Memorial Arena")
                            .availablePlace(900)
                            .date(LocalDate.of(2024, 1, 29))
                            .price(new BigDecimal("120.00"))
                            .build(),
                    Concert.builder()
                            .city("Columbus")
                            .band("Guns N' Roses")
                            .reply("Nationwide Arena")
                            .availablePlace(800)
                            .date(LocalDate.of(2024, 5, 9))
                            .price(new BigDecimal("135.00"))
                            .build(),
                    Concert.builder()
                            .city("Charlotte")
                            .band("Bon Jovi")
                            .reply("Spectrum Center")
                            .availablePlace(1000)
                            .date(LocalDate.of(2024, 6, 23))
                            .price(new BigDecimal("125.00"))
                            .build(),
                    Concert.builder()
                            .city("Fort Worth")
                            .band("Aerosmith")
                            .reply("Dickies Arena")
                            .availablePlace(1400)
                            .date(LocalDate.of(2024, 7, 10))
                            .price(new BigDecimal("145.00"))
                            .build(),
                    Concert.builder()
                            .city("Indianapolis")
                            .band("The Eagles")
                            .reply("Gainbridge Fieldhouse")
                            .availablePlace(1200)
                            .date(LocalDate.of(2024, 8, 2))
                            .price(new BigDecimal("135.00"))
                            .build(),
                    Concert.builder()
                            .city("Seattle")
                            .band("Pearl Jam")
                            .reply("Climate Pledge Arena")
                            .availablePlace(1100)
                            .date(LocalDate.of(2024, 9, 13))
                            .price(new BigDecimal("140.00"))
                            .build(),
                    Concert.builder()
                            .city("Denver")
                            .band("Fleetwood Mac")
                            .reply("Ball Arena")
                            .availablePlace(1300)
                            .date(LocalDate.of(2024, 10, 20))
                            .price(new BigDecimal("150.00"))
                            .build(),
                    Concert.builder()
                            .city("Washington")
                            .band("Journey")
                            .reply("Capital One Arena")
                            .availablePlace(1000)
                            .date(LocalDate.of(2024, 11, 18))
                            .price(new BigDecimal("130.00"))
                            .build(),
                    Concert.builder()
                            .city("Boston")
                            .band("The Beach Boys")
                            .reply("TD Garden")
                            .availablePlace(900)
                            .date(LocalDate.of(2024, 12, 5))
                            .price(new BigDecimal("115.00"))
                            .build(),
                    Concert.builder()
                            .city("Nashville")
                            .band("Nirvana Tribute")
                            .reply("Bridgestone Arena")
                            .availablePlace(800)
                            .date(LocalDate.of(2024, 1, 12))
                            .price(new BigDecimal("110.00"))
                            .build()
            ));
        }
    }



    private void initializeTickets() {
        if (ticketRepository.count() == 0) {
            List<Concert> concerts = concertRepository.findAll();
            concerts.forEach(concert -> {
                Ticket ticket = Ticket.builder()
                        .concert(concert)
                        .availableQta(concert.getAvailablePlace())
                        .build();
                ticketRepository.save(ticket);
            });
        }
    }

    private void initializePrenotations() {
        if (prenotationRepository.count() == 0) {
            List<User> users = userRepository.findAll();
            List<Ticket> tickets = ticketRepository.findAll();
            if (!users.isEmpty() && !tickets.isEmpty()) {
                Prenotation prenotation1 = Prenotation.builder()
                        .user(users.get(0))
                        .qta(2)
                        .paymentType(PaymentType.CREDIT_CARD)
                        .totalPrice(new BigDecimal("300.00"))
                        .ticket(tickets.get(0))
                        .build();
                prenotationRepository.save(prenotation1);

                Prenotation prenotation2 = Prenotation.builder()
                        .user(users.get(1))
                        .qta(3)
                        .paymentType(PaymentType.PAYPAL)
                        .totalPrice(new BigDecimal("360.00"))
                        .ticket(tickets.get(1))
                        .build();
                prenotationRepository.save(prenotation2);
            }
        }
    }
}