package com.booleanuk.api.cinema.repositories;

import com.booleanuk.api.cinema.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByCustomerIdAndScreeningId(Long customerId, Long screeningId);
}
