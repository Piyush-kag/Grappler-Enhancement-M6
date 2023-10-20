package com.example.grappler.Controllers;
import com.example.grappler.Entity.Tickets;
import com.example.grappler.Repositories.TicketRepository;
import com.example.grappler.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;
    @Autowired
    private TicketRepository ticketRepository;
    @GetMapping("/")
    public ResponseEntity<List<Tickets>> getAllTickets() {
        List<Tickets> tickets = ticketRepository.findAll();
        for (Tickets ticket : tickets) {
            System.out.println("Ticket: {}"+ ticket.toString());
        }
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Tickets> createTicket(@RequestBody Tickets ticket) {
        Tickets newTicket = ticketService.addTicket(ticket);
        return new ResponseEntity<>(newTicket, HttpStatus.CREATED);
    }


}
