package com.example.socialnet.Repositories;

import com.example.socialnet.Entities.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepo extends JpaRepository<Vote,Long> {
}
