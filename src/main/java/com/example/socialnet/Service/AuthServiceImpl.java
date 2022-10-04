package com.example.socialnet.Service;

import com.example.socialnet.DataTransferClasses.LoginRequest;
import com.example.socialnet.DataTransferClasses.RegisterRequest;
import com.example.socialnet.Entities.EmailNotification;
import com.example.socialnet.Entities.User;
import com.example.socialnet.Entities.VerificationToken;
import com.example.socialnet.Exceptions.InexistingUserException;
import com.example.socialnet.Exceptions.VerificationException;
import com.example.socialnet.JWT.AuthenticationToken;
import com.example.socialnet.JWT.JwtProvider;
import com.example.socialnet.Repositories.UserRepo;
import com.example.socialnet.Repositories.VerificationTokenRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final VerificationTokenRepo verificationTokenRepo;
    private  final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    @Override
    public void signup(RegisterRequest registerRequest) {
        User user=new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreatedAt(Instant.now());
        user.setEnabled(false);
        userRepo.save(user);
        String token=generateVerificationToken(user);
        mailService.sendMail(new EmailNotification("Please activate your account",
                user.getEmail(),"thanks for signin if you want to verify your account" +
                "please click : http://localhost:4443/api/auth/accountverification/"+token));
    }

    @Override
    public String generateVerificationToken(User user) {
        String vToken=UUID.randomUUID().toString();
        VerificationToken verificationToken=new VerificationToken();
        verificationToken.setToken(vToken);
        verificationToken.setUser(user);
        verificationTokenRepo.save(verificationToken);
        return vToken;



    }

    @Override
    public void verifyAccount(String token) {
      Optional<VerificationToken> verificationToken=verificationTokenRepo.findByToken(token);
      verificationToken.orElseThrow(()->new VerificationException("Invalid token")
      );
      findUserAndEnable(verificationToken.get());

    }

    @Override
    public void findUserAndEnable(VerificationToken verificationToken) {
      String username=  verificationToken.getUser().getUsername();
     User user= userRepo.findByUsername(username).orElseThrow(()->new InexistingUserException("user not found") );
     user.setEnabled(true);
     userRepo.save(user);
    }

    @Override
    public AuthenticationToken login(LoginRequest loginRequest) {
       Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token= jwtProvider.generateToken(authentication);
        return new AuthenticationToken(token,loginRequest.getUsername());


    }
}
