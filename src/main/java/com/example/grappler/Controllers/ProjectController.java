package com.example.grappler.Controllers;

import com.example.grappler.Entity.Project;
import com.example.grappler.Entity.Tickets;
import com.example.grappler.Exception.ResourceNotFoundException;
import com.example.grappler.Repositories.ProjectRepository;
import com.example.grappler.services.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectService projectService;


    Logger logger = LoggerFactory.getLogger(TicketController.class);

    //All Projects
    @GetMapping
    public ResponseEntity<?> getAllProjects() {
        try {
            System.out.println("Fetching all projects...");
            List<Project> projects = projectRepository.findAll();
            if (projects.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No projects Found");
            } else {
                return ResponseEntity.ok(projects);
            }
        } catch (Exception e) {
            System.err.println("Please Enter Valid Id : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while fetching projects.");
        }
    }

    //Get Project by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getProjectById(@PathVariable Long id) {
        try {
            Optional<Project> project = projectRepository.findById(id);
            if (project.isPresent()) {
                return ResponseEntity.ok(project.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while fetching the project.");
        }
    }

    //Add Project
    @PostMapping
    public ResponseEntity<?> addProject(@RequestBody Project project) {
        try {
            Project savedProject = projectService.addProject(project);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProject);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while creating the project.");
        }
    }
}
