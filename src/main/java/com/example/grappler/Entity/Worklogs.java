package com.example.grappler.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
@Entity
public class Worklogs {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)private Long log_id;

    @ManyToOne
    private Users users;

//    @ManyToOne
//    private Project projects;

    @ManyToOne
    @JoinColumn(name = "ticket_id") // Specify the foreign key column name
    private Tickets ticket;

    private LocalDateTime date;

    private LocalDateTime start_time;//min
    private LocalDateTime end_time; //min
    private int duration; //min

    public void setTicketId(Long ticketId) {
    }
    public void calculateAndSetDuration() {
        if (start_time != null && end_time != null) {
            Duration duration = Duration.between(start_time, end_time);
            this.duration = (int) duration.toMinutes(); // Duration in minutes
        }
    }

}
