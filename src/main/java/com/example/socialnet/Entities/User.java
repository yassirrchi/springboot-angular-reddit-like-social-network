package com.example.socialnet.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long userId;
    @NotBlank(message = "username cant be empty")
    private String username;
    @NotBlank(message = "Password cantbe empty")
    private String password;
    @Email
    @NotBlank(message = "Email is required")
    private String email;
    private Instant createdAt;
    private boolean enabled;


}
