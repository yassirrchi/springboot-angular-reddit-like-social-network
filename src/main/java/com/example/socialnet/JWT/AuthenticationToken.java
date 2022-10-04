package com.example.socialnet.JWT;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationToken {
    private String authenticationToken;
    private String username;
}
