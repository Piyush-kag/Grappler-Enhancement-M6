package com.example.grappler.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Data
@Entity
public class Users {

    @Id@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String username;
    private  String name;
    private String email;
    private Role role;

    @OneToMany(mappedBy = "users" ,cascade=CascadeType.ALL)
    @JsonIgnore
    private List<Worklogs> worklogs;

    @ManyToMany
    @JsonIgnore
    private List<Project> projects;

    @ManyToOne
    private Planed planed;

    public Users() {
    }

    public Users(Long id, String username, String name, String email, Role role, List<Worklogs> worklogs, List<Project> projects, Planed planed) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.email = email;
        this.role = role;
        this.worklogs = worklogs;
        this.projects = projects;
        this.planed = planed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

