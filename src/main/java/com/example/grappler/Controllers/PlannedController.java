// PlanedController.java
package com.example.grappler.Controllers;

import com.example.grappler.Entity.Planed;
import com.example.grappler.services.PlanedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/planned")
public class PlannedController {
    private PlanedService planedService;

    @Autowired
    public PlannedController(PlanedService planedService) {
        this.planedService = planedService;
    }

    @GetMapping("/all")
    public List<Planed> getAllPlaned() {
        return planedService.getAllPlaned();
    }
}
