package com.example.socialnet.Service;

import com.example.socialnet.DataTransferClasses.LoginRequest;
import com.example.socialnet.DataTransferClasses.RegisterRequest;
import com.example.socialnet.Entities.User;
import com.example.socialnet.Entities.VerificationToken;
import com.example.socialnet.JWT.AuthenticationToken;


public interface AuthService {
    public void signup(RegisterRequest registerRequest);
    public String generateVerificationToken(User user);
    public void verifyAccount(String token);
    public void findUserAndEnable(VerificationToken verificationToken);
    public AuthenticationToken login(LoginRequest loginRequest);

}
