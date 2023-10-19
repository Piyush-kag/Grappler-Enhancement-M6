package com.example.grappler.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Users {

    @Id@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String username;
    private  String name;
    private String email;
    private Role role;
//    @OneToMany(mappedBy = "users" ,cascade=CascadeType.ALL)
// private List<Project> projects;

    @OneToMany(mappedBy = "users" ,cascade=CascadeType.ALL)
    private List<Ticket_assign> ticket_assign;

    @OneToMany(mappedBy = "users" ,cascade=CascadeType.ALL)
    private List<Worklogs> worklogs;


    @OneToMany(mappedBy = "users",cascade =CascadeType.ALL)
    private List<Planed> planed;

    public Users() {
    }

    public Users(Long id, String username, String name, String email) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.email = email;
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

