package com.example.grappler.services;

import com.example.grappler.Entity.Tickets;
import com.example.grappler.Repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    /**
     *
     * @return all tickets
     */
    public List<Tickets> getAllTickets() {
        return ticketRepository.findAll();
    }
    public Tickets addTicket(Tickets newTicket) {
        return ticketRepository.save(newTicket);
    }

}
