package com.example.socialnet.Service;

import com.example.socialnet.DataTransferClasses.RegisterRequest;
import com.example.socialnet.Entities.User;
import com.example.socialnet.Entities.VerificationToken;
import com.example.socialnet.Repositories.UserRepo;
import com.example.socialnet.Repositories.VerificationTokenRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final VerificationTokenRepo verificationTokenRepo;
    @Override
    public void signup(RegisterRequest registerRequest) {
        User user=new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());
        user.setCreatedAt(Instant.now());
        user.setEnabled(false);
        userRepo.save(user);
        generateVerificationToken(user);
    }

    @Override
    public void generateVerificationToken(User user) {
        String vToken=UUID.randomUUID().toString();
        VerificationToken verificationToken=new VerificationToken();
        verificationToken.setToken(vToken);
        verificationToken.setUser(user);
        verificationTokenRepo.save(verificationToken);



    }
}
