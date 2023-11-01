package com.example.grappler.Controllers;

import com.example.grappler.Entity.Users;
import com.example.grappler.Repositories.UserRepository;
import com.example.grappler.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }

}
