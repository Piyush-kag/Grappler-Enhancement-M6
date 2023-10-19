package com.example.grappler.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Tickets {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)@Column(name ="ticket_id",nullable=false )Long ticket_id;

    @ManyToOne
    private AssignmentHistory assignmentHistory;

    @OneToMany(mappedBy = "tickets" ,cascade=CascadeType.ALL)
    private List<Worklogs> worklogs;

    @OneToMany(mappedBy = "tickets" ,cascade=CascadeType.ALL)
    private List<Ticket_assign>  ticket_assign ;


    @OneToMany(mappedBy = "tickets",cascade =CascadeType.ALL)
    private List<Planed> planed;
 String title;
 String desciption;
    //String status;
    private LocalDateTime estiLocalDateTime;
    String priority;

}
