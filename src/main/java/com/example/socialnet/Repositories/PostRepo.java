package com.example.socialnet.Repositories;

import com.example.socialnet.Entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post,Long> {
}
