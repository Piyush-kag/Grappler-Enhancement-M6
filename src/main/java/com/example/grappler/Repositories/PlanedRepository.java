package com.example.grappler.Repositories;

import com.example.grappler.Entity.Planed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanedRepository extends JpaRepository<Planed ,Long> {
}
