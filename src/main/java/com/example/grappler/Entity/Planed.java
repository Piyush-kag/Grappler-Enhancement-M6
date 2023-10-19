package com.example.grappler.Entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity

public class Planed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planId;

    @ManyToOne
    private Users users;

    @ManyToOne
    private Tickets tickets;

    @ManyToOne
    private Project projects;

    private String priority;
private LocalDateTime start;
private LocalDateTime end;
    // Getters and setters
}
