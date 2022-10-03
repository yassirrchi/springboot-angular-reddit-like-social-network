package com.example.socialnet.Repositories;

import com.example.socialnet.Entities.Subreddit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubRedditRepo extends JpaRepository<Subreddit,Long> {
}
