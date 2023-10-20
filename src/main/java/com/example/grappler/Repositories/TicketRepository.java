package com.example.grappler.Repositories;

import com.example.grappler.Entity.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Tickets, Long> {
}
