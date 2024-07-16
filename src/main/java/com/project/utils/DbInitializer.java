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
            Concert concert1 = Concert.builder()
                    .city("New York")
                    .band("The Rolling Stones")
                    .reply("Madison Square Garden")
                    .availablePlace(1000)
                    .date(LocalDate.of(2024, 8, 15))
                    .price(new BigDecimal("150.00"))
                    .build();
            concertRepository.save(concert1);

            Concert concert2 = Concert.builder()
                    .city("Los Angeles")
                    .band("Coldplay")
                    .reply("SoFi Stadium")
                    .availablePlace(1500)
                    .date(LocalDate.of(2024, 9, 20))
                    .price(new BigDecimal("120.00"))
                    .build();
            concertRepository.save(concert2);
        }
    }

    private void initializeTickets() {
        if (ticketRepository.count() == 0) {
            List<Concert> concerts = concertRepository.findAll();
            if (!concerts.isEmpty()) {
                Ticket ticket1 = Ticket.builder()
                        .concert(concerts.get(0))
                        .availableQta(1000)
                        .build();
                ticketRepository.save(ticket1);

                Ticket ticket2 = Ticket.builder()
                        .concert(concerts.get(1))
                        .availableQta(1500)
                        .build();
                ticketRepository.save(ticket2);
            }
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