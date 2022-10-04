package com.example.socialnet.Repositories;

import com.example.socialnet.Entities.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationTokenRepo extends JpaRepository<VerificationToken,Long> {

    Optional<VerificationToken> findByToken(String token);
}
