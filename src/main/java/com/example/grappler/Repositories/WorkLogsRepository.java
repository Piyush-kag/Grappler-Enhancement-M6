package com.example.grappler.Repositories;

import com.example.grappler.Entity.Worklogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface WorkLogsRepository extends JpaRepository<Worklogs,Long> {
        
    public List<Worklogs> findByTicketTicketId(Long ticketId);
}
