package com.example.grappler.Repositories;

import com.example.grappler.Entity.Worklogs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkLogsRepository extends JpaRepository<Worklogs,Long> {
}
