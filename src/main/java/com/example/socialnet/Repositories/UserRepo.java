package com.example.socialnet.Repositories;

import com.example.socialnet.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
   Optional<User> findByUsername(String username);
}
