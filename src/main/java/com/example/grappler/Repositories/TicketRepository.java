package com.example.grappler.Repositories;

import com.example.grappler.Entity.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TicketRepository extends JpaRepository<Tickets, Long> {

    @Query("SELECT t FROM Tickets t JOIN t.users u WHERE :id = u.id")
    List<Tickets> findTicketsByUserId(@Param("id") Long id);

}

