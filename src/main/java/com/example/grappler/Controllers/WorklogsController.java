package com.example.grappler.Controllers;

import com.example.grappler.Entity.Tickets;
import com.example.grappler.Entity.Worklogs;
import com.example.grappler.Repositories.TicketRepository;
import com.example.grappler.services.WorklogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/worklogs")
public class WorklogsController {

    @Autowired
    private WorklogsService worklogsService;

    @Autowired
    private TicketRepository ticketRepository;

    @PostMapping("/{ticketId}")
    public Worklogs addWorklog(@PathVariable Long ticketId, @RequestBody Worklogs worklog) {
        Optional<Tickets> ticket = ticketRepository.findById(ticketId);
        worklog.setStart_time(worklog.getStart_time());
        worklog.setEnd_time(worklog.getEnd_time());
        worklog.setTicketId(ticketId);
        worklog.calculateAndSetDuration();
        return worklogsService.addWorklog(worklog);
    }

}
