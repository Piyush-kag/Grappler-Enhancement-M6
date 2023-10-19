package com.example.grappler.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Worklogs {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)private Long log_id;

    @ManyToOne
    private Users users;

    @ManyToOne
    private Project projects;

    @ManyToOne
    private Tickets tickets;

    private LocalDateTime date;

    private LocalDateTime start_time;//min
    private LocalDateTime end_time; //min
    private Integer duration; //min
}
