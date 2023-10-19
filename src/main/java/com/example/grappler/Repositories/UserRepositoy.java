package com.example.grappler.Repositories;

import com.example.grappler.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoy extends JpaRepository<Users, Long> {
}
