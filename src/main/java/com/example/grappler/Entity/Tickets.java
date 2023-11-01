package com.example.grappler.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Tickets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id", nullable = false)
    private Long ticketId;

    String title;
    String desciption;
    String priority;
    private int estimated_time;
    private LocalDateTime start_time;
    private LocalDateTime end_time;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
    private List<Worklogs> worklogs;

    @ManyToOne
    @JsonIgnore
    private Planed planed;

    @ManyToOne
    @JsonIgnore
    private AssignmentHistory assignmentHistory;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "project_id")
    private Project project;

    private List<Long> userIds;

    @ManyToMany
    @JoinTable(name = "ticket_user",
            joinColumns = @JoinColumn(name = "ticketId"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<Users> users;



    public void setProject(Project project) {
        this.project = project;
    }

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }


    public List<Worklogs> getWorklogs() {
        return worklogs;
    }

    public void setWorklogs(List<Worklogs> worklogs) {
        this.worklogs = worklogs;
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


    public int getEstimated_time() {
        return estimated_time;
    }

    public void setEstimated_time(int estimated_time) {
        this.estimated_time = estimated_time;
    }

    @Override
    public String toString() {
        return "Tickets{" +
                "ticket_id=" + ticketId +
                ", title='" + title + '\'' +
                ", description='" + desciption + '\'' +
                ", priority='" + priority + '\'' +
                ", start_Time='" + start_time+ '\'' +
                ", estimated_Time='" + estimated_time+ '\'' +
                ", end_Time='" + end_time+ '\'' +
                '}';
    }

}