package com.example.grappler.Controllers;

import com.example.grappler.Entity.Worklogs;
import com.example.grappler.Repositories.TicketRepository;
import com.example.grappler.Repositories.UserRepository;
import com.example.grappler.Repositories.WorkLogsRepository;
import com.example.grappler.services.WorklogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/worklogs")
public class WorklogsController {
@Autowired
private WorkLogsRepository workLogsRepository;
    @Autowired
    private WorklogsService worklogsService;

    @Autowired
    private TicketRepository    ticketRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public ResponseEntity<List<Worklogs>> getAllWorklogs() {
        try {
            List<Worklogs> worklogs = worklogsService.getAllWorklogs();
            if (worklogs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(worklogs, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/by-ticket/{ticketId}")
    public ResponseEntity<List<Worklogs>> getWorklogsByTicketId(@PathVariable(name = "ticketId") Long ticketId) {
        if (ticketId == null || ticketId <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            List<Worklogs> worklogs = worklogsService.getWorklogsByTicketId(ticketId);
            if (worklogs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(worklogs, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
