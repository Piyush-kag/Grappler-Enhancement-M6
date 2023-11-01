package com.example.grappler.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Planed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planId;

    @OneToMany(mappedBy = "planed" ,cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Users> users;

    @OneToMany(mappedBy = "planed" ,cascade=CascadeType.ALL)
    private List<Tickets> tickets;

    @OneToMany(mappedBy = "planed" ,cascade=CascadeType.ALL)
    private List<Project> projects;

    private String priority;
private LocalDateTime start;
private LocalDateTime end;


    // Getters and setters
}
