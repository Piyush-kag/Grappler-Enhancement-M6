// PlanedService.java
package com.example.grappler.services;

import com.example.grappler.Entity.Planed;
import com.example.grappler.Repositories.PlanedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanedService {
    private final PlanedRepository planedRepository;

    @Autowired
    public PlanedService(PlanedRepository planedRepository) {
        this.planedRepository = planedRepository;
    }

    public List<Planed> getAllPlaned() {
        return planedRepository.findAll();
    }

    public Optional<Planed> getPlanedById(Long id) {
        return planedRepository.findById(id);
    }

    public Planed addPlaned(Planed planed) {
        return planedRepository.save(planed);
    }

    public Planed updatePlaned(Long id, Planed planed) {
        if (planedRepository.existsById(id)) {
            planed.setPlanId(id);
            return planedRepository.save(planed);
        } else {
            throw new IllegalArgumentException("Planed with ID " + id + " not found.");
        }
    }

    public void deletePlaned(Long id) {
        if (planedRepository.existsById(id)) {
            planedRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Planed with ID " + id + " not found.");
        }
    }
}
