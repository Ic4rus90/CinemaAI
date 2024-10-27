package com.booleanuk.api.cinema.services;

import com.booleanuk.api.cinema.models.Customer;
import com.booleanuk.api.cinema.models.Screening;
import com.booleanuk.api.cinema.models.Ticket;
import com.booleanuk.api.cinema.repositories.CustomerRepository;
import com.booleanuk.api.cinema.repositories.ScreeningRepository;
import com.booleanuk.api.cinema.repositories.TicketRepository;
import com.booleanuk.api.cinema.exceptions.ResourceNotFoundException;
import com.booleanuk.api.cinema.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ScreeningRepository screeningRepository;

    public Ticket createTicket(Long customerId, Long screeningId, Ticket ticket) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("No customer with that ID found"));

        Screening screening = screeningRepository.findById(screeningId)
                .orElseThrow(() -> new ResourceNotFoundException("No screening with that ID found"));

        if (screening.getCapacity() < ticket.getNumSeats()) {
            throw new BadRequestException("Not enough seats available");
        }

        screening.setCapacity(screening.getCapacity() - ticket.getNumSeats());
        screeningRepository.save(screening);

        ticket.setCustomer(customer);
        ticket.setScreening(screening);
        return ticketRepository.save(ticket);
    }

    public List<Ticket> getTickets(Long customerId, Long screeningId) {
        if (!customerRepository.existsById(customerId) || !screeningRepository.existsById(screeningId)) {
            throw new ResourceNotFoundException("No customer or screening with those IDs found");
        }
        return ticketRepository.findByCustomerIdAndScreeningId(customerId, screeningId);
    }
}
