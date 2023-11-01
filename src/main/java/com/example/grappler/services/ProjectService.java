package com.example.grappler.services;

import com.example.grappler.Entity.Tickets;
import com.example.grappler.Exception.ProjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.grappler.Entity.Project;
import com.example.grappler.Repositories.ProjectRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }
    /**
     * Create and save a new project.
     *
     * @param project The project to be created.
     * @return The saved project.
     * @throws IllegalArgumentException if project is null.
     */
    public Project addProject(Project project) {
        if (project == null) {
            throw new IllegalArgumentException("Project cannot be null.");
        }
        Project newProject = new Project();
        newProject.setName(project.getName());
        newProject.setDescription(project.getDescription());
        newProject.setStart_date(project.getStart_date());
        newProject.setEnd_date(project.getEnd_date());
        return projectRepository.save(newProject);
    }
}
