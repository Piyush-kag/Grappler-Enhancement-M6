package com.example.grappler.Repositories;

import com.example.grappler.Entity.Project;
import com.example.grappler.Entity.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Tickets> findByProjectId(Long projectId); // Use the existing property name
}
