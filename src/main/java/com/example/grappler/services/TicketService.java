package com.example.grappler.services;

import com.example.grappler.Entity.Tickets;
import com.example.grappler.Entity.Users;
import com.example.grappler.Exception.TicketNotFoundException;
import com.example.grappler.Exception.UserNotFoundException;
import com.example.grappler.Repositories.TicketRepository;
import com.example.grappler.Repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for managing tickets and related operations.
 */
@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private UserRepository userRepository;

    Logger logger = LoggerFactory.getLogger(TicketService.class);

    /**
     * Retrieve a list of all tickets.
     *
     * @return List of all tickets
     */
    public List<Tickets> getAllTickets() {
        logger.info("Retrieving all tickets.");
        List<Tickets> tickets = ticketRepository.findAll();
        logger.info("Retrieved {} tickets.", tickets.size());
        return tickets;
    }

    /**
     * Retrieve a ticket by its ID.
     *
     * @param ticketId The ID of the ticket to retrieve.
     * @return The ticket with the specified ID
     * @throws TicketNotFoundException if the specified ticket is not found
     */
    public Tickets getTicketById(Long ticketId) {
        logger.info("Retrieving ticket with ID: {}.", ticketId);
        Tickets ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found with ID: " + ticketId));
        logger.info("Retrieved ticket with ID: {}.", ticketId);
        return ticket;
    }

    /**
     * Create a new ticket and save it.
     *
     * @param ticket The ticket to create and save
     * @return The created ticket
     * @throws IllegalArgumentException if the user IDs in the ticket are null
     * @throws UserNotFoundException if a user with the specified ID is not found
     */
    public Tickets createTicket(Tickets ticket) {
        validateTicket(ticket); // You can add validation logic here.

        List<Users> users = getUsersFromUserIds(ticket.getUserIds());
        ticket.setUsers(users);

        return ticketRepository.save(ticket);
    }

    private void validateTicket(Tickets ticket) {
        if (ticket.getUserIds() == null) {
            throw new IllegalArgumentException("User IDs cannot be null.");
        }
        // Add more validation logic here if needed.
    }

    private List<Users> getUsersFromUserIds(List<Long> userIds) {
        List<Users> users = new ArrayList<>();
        for (Long userId : userIds) {
            Users user = userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException("User not found"));
            users.add(user);
        }
        return users;
    }

    /**
     * Get a list of tickets associated with a specific user.
     *
     * @param userId The ID of the user to retrieve tickets for
     * @return List of tickets associated with the specified user
     */
    public List<Tickets> getTicketsByUserId(Long userId) {
        return ticketRepository.findTicketsByUserId(userId);
    }
}
