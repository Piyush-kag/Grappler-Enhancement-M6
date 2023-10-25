package com.example.grappler.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Tickets {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)@Column(name ="ticket_id",nullable=false )Long ticket_id;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
    private List<Worklogs> worklogs;

    @OneToMany(mappedBy = "tickets",cascade =CascadeType.ALL)
    private List<Planed> planed;

    @ManyToMany
    @JoinTable(name = "ticket_user",
            joinColumns = @JoinColumn(name = "ticket_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<Users> users;

    @ManyToOne
    private AssignmentHistory assignmentHistory;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    private List<Long> userIds;

    public LocalDateTime getStart_time() {
        return start_time;
    }

    public void setStart_time(LocalDateTime startTime) {
        this.start_time = startTime;
    }

    public void setEnd_time(LocalDateTime endTime) {
        this.end_time = endTime;
    }



    public void setProject(Project project) {
        this.project = project;
    }


    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }


    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }

    public Long getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(Long ticket_id) {
        this.ticket_id = ticket_id;
    }


    public List<Worklogs> getWorklogs() {
        return worklogs;
    }

    public void setWorklogs(List<Worklogs> worklogs) {
        this.worklogs = worklogs;
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


    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }


    String title;
    String desciption;
    //String status;
    String priority;
    private int estimated_time;
    private LocalDateTime start_time;
    private LocalDateTime end_time;

    public int getEstimated_time() {
        return estimated_time;
    }

    public void setEstimated_time(int estimated_time) {
        this.estimated_time = estimated_time;
    }

    @Override
    public String toString() {
        return "Tickets{" +
                "ticket_id=" + ticket_id +
                ", title='" + title + '\'' +
                ", description='" + desciption + '\'' +
                ", priority='" + priority + '\'' +
                ", start_Time='" + start_time+ '\'' +
                ", estimated_Time='" + estimated_time+ '\'' +
                ", end_Time='" + end_time+ '\'' +
                '}';
    }

}