package com.booleanuk.api.cinema.controllers;

import com.booleanuk.api.cinema.models.Ticket;
import com.booleanuk.api.cinema.services.TicketService;
import com.booleanuk.api.cinema.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers/{customerId}/screenings/{screeningId}")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping
    public ResponseEntity<ApiResponse> createTicket(@PathVariable Long customerId,
                                                    @PathVariable Long screeningId,
                                                    @RequestBody Ticket ticket) {
        Ticket createdTicket = ticketService.createTicket(customerId, screeningId, ticket);
        return new ResponseEntity<>(new ApiResponse("success", createdTicket), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getTickets(@PathVariable Long customerId,
                                                  @PathVariable Long screeningId) {
        return new ResponseEntity<>(new ApiResponse("success", ticketService.getTickets(customerId, screeningId)), HttpStatus.OK);
    }
}
