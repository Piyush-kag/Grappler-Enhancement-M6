package com.example.grappler.Controllers;
import com.example.grappler.Entity.Tickets;
import com.example.grappler.Entity.Users;
import com.example.grappler.Exception.ProjectNotFoundException;
import com.example.grappler.Exception.TicketNotFoundException;
import com.example.grappler.Exception.UserNotFoundException;
import com.example.grappler.Repositories.ProjectRepository;
import com.example.grappler.Repositories.TicketRepository;
import com.example.grappler.Repositories.UserRepositoy;
import com.example.grappler.services.ProjectService;
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
    private UserRepositoy userRepositoy;

    Logger logger= LoggerFactory.getLogger(TicketController.class);


    /**
     * Retrieve a list of all tickets.
     *
     * @return ResponseEntity containing a list of all tickets.
     */
    @GetMapping
    public ResponseEntity<List<Tickets>> getAllTickets() {
        try {
            List<Tickets> tickets = ticketService.getAllTickets();
            for (Tickets ticket : tickets) {
                logger.info("Ticket: {}", ticket.toString());
            }
            return new ResponseEntity<>(tickets, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("An error occurred while getting tickets: " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Retrieve a specific ticket by its ID.
     *
     * @param ticketId The ID of the ticket to retrieve.
     * @return ResponseEntity containing the ticket with the specified ID.
     */
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
            throw new RuntimeException( "Failed to retrieve the ticket.", e);
        }
    }

    /**
     * Create a new ticket.
     *
     * @param ticket The ticket object to be created.
     * @return ResponseEntity with details of the created ticket or an error message.
     */
    @PostMapping
    public ResponseEntity<?> createTicket(@RequestBody Tickets ticket) {
        try {
            if (ticket.getUserIds() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User IDs cannot be null.");
            }
            List<Users> users = new ArrayList<>();
            for (Long userId : ticket.getUserIds()) {
                Users user = userRepositoy.findById(userId)
                        .orElseThrow(() -> new UserNotFoundException("User not found"));
                users.add(user);
            }
            Tickets ticket1 = new Tickets();
            ticket1.setTitle(ticket.getTitle());
            ticket1.setDesciption(ticket.getDesciption());
            ticket1.setEstimated_time(ticket.getEstimated_time());
            ticket1.setPriority(ticket.getPriority());
            ticket1.setUserIds(ticket.getUserIds());
            ticket1.setUsers(users);
            ticket1.setStart_time(ticket.getStart_time());
            ticket1.setEnd_time(ticket.getEnd_time());
            Tickets createdTicket = ticketService.createTicket(ticket1);

            return new ResponseEntity<>(createdTicket, HttpStatus.CREATED);
        } catch (UserNotFoundException e) {
            logger.error("User not found: " + e.getMessage(), e);
            return new ResponseEntity<>("User not found: " + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("An error occurred while creating a ticket: " + e.getMessage(), e);
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
}
}
