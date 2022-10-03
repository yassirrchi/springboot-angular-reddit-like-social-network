package com.example.socialnet.Service;

import com.example.socialnet.DataTransferClasses.RegisterRequest;
import com.example.socialnet.Entities.User;


public interface AuthService {
    public void signup(RegisterRequest registerRequest);
    public String generateVerificationToken(User user);
}
