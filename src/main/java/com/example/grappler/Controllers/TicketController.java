package com.example.grappler.Controllers;

import com.example.grappler.Entity.Project;
import com.example.grappler.Entity.Tickets;
import com.example.grappler.Entity.Users;
import com.example.grappler.Exception.ProjectNotFoundException;
import com.example.grappler.Exception.TicketNotFoundException;
import com.example.grappler.Exception.UserNotFoundException;
import com.example.grappler.Repositories.UserRepository;
import com.example.grappler.services.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
            private UserRepository userRepository;
    Logger logger = LoggerFactory.getLogger(TicketController.class);

    @GetMapping
    public ResponseEntity<List<Tickets>> getAllTickets() {
        try {
            List<Tickets> tickets = ticketService.getAllTickets();
            return new ResponseEntity<>(tickets, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("An error occurred while getting tickets: " + e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<Tickets> getTicketById(@PathVariable Long ticketId) {
        try {
            Tickets ticket = ticketService.getTicketById(ticketId);
            logger.info("Retrieved ticket with ID: {}", ticketId);
            return new ResponseEntity<>(ticket, HttpStatus.OK);
        } catch (TicketNotFoundException ex) {
            logger.error("Ticket not found with ID: {}", ticketId, ex);
            throw ex;
        } catch (Exception e) {
            logger.error("Failed to retrieve ticket with ID: {}", ticketId, e);
            throw new RuntimeException("Failed to retrieve the ticket.", e);
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Tickets>> getTicketsByUserId(@PathVariable Long id) {
        try {
            List<Tickets> tickets = ticketService.getTicketsByUserId(id);
            if (tickets.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                logger.info("Retrieved tickets for User ID: {}", id);
                return new ResponseEntity<>(tickets, HttpStatus.OK);
            }
        } catch (Exception e) {
            logger.error("Failed to retrieve tickets for User ID: {}", id, e);
            throw new RuntimeException("Failed to retrieve the tickets.", e);
        }
    }

    @PostMapping
    public ResponseEntity<?> createTicket(@RequestBody Tickets ticket) {
        try {
            Tickets createdTicket = ticketService.createTicket(ticket);
            logger.info("Created a new ticket with ID: {}", createdTicket.getTicketId());
            return new ResponseEntity<>(createdTicket, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            logger.error("Bad request: " + e.getMessage(), e);
            return new ResponseEntity<>("Bad request: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (UserNotFoundException e) {
            logger.error("User not found: " + e.getMessage(), e);
            return new ResponseEntity<>("User not found: " + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("An error occurred while creating a ticket: " + e.getMessage(), e);
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
