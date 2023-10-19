package com.example.grappler.Controllers;

import com.example.grappler.Entity.Project;
import com.example.grappler.Repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectRepository projectRepository;



    //All Projects
    @GetMapping("/")
    public ResponseEntity<?> getAllProjects() {
        try {
            List<Project> projects = projectRepository.findAll();
            if (projects.isEmpty()) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(projects);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while fetching projects.");
        }
    }

    //Get Project by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getProjectById(@PathVariable Long id) {
        try {
            Optional<Project> project = projectRepository.findById(id);
//            Optional<Project> project = projectRepository.findById(id);
            project.ifPresent(p -> System.out.println(p));
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
    @PostMapping("/")
    public ResponseEntity<?> addProject(@RequestBody Project project) {
        try {
            // Your existing logic to create and save the project
            Project newProject = new Project();
            newProject.setName(project.getName());
            newProject.setDescription(project.getDescription());
            newProject.setStart_date(project.getStart_date());
            newProject.setEnd_date(project.getEnd_date());

            Project savedProject = projectRepository.save(newProject);
            // Return a 201 Created response with the saved project in the response body
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProject);
        } catch (Exception e) {
            // Handle any unexpected errors and return a 500 Internal Server Error response.
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while creating the project.");
        }
    }

    //Delete project By Id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isPresent()) {
            projectRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Update Project By Id
    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project updatedProject) {
        Optional<Project> existingProject = projectRepository.findById(id);
        if (existingProject.isPresent()) {
            Project project = existingProject.get();
            project.setName(updatedProject.getName());
            project.setDescription(updatedProject.getDescription());
            project.setStart_date(updatedProject.getStart_date());
            project.setEnd_date(updatedProject.getEnd_date());

            Project updated = projectRepository.save(project);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
