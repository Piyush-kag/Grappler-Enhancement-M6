package com.example.grappler.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Tickets {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)@Column(name ="ticket_id",nullable=false )Long ticket_id;

    public Long getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(Long ticket_id) {
        this.ticket_id = ticket_id;
    }

    public AssignmentHistory getAssignmentHistory() {
        return assignmentHistory;
    }

    public void setAssignmentHistory(AssignmentHistory assignmentHistory) {
        this.assignmentHistory = assignmentHistory;
    }

    public List<Worklogs> getWorklogs() {
        return worklogs;
    }

    public void setWorklogs(List<Worklogs> worklogs) {
        this.worklogs = worklogs;
    }

    public List<Ticket_assign> getTicket_assign() {
        return ticket_assign;
    }

    public void setTicket_assign(List<Ticket_assign> ticket_assign) {
        this.ticket_assign = ticket_assign;
    }

    public List<Planed> getPlaned() {
        return planed;
    }

    public void setPlaned(List<Planed> planed) {
        this.planed = planed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public int getEstimated_time() {
        return estimated_time;
    }

    public void setEstimated_time(int estimated_time) {
        this.estimated_time = estimated_time;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

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
    private int estimated_time;
    String priority;

    @Override
    public String toString() {
        return "Tickets{" +
                "ticket_id=" + ticket_id +
                ", title='" + title + '\'' +
                ", description='" + desciption + '\'' +
                ", priority='" + priority + '\'' +
                ", estimated_Time='" + estimated_time+ '\'' +
                '}';
    }

}
