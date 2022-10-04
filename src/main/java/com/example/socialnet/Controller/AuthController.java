package com.example.socialnet.Controller;

import com.example.socialnet.DataTransferClasses.LoginRequest;
import com.example.socialnet.DataTransferClasses.RegisterRequest;
import com.example.socialnet.JWT.AuthenticationToken;
import com.example.socialnet.Service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    @PostMapping("/signup")
    public ResponseEntity<String> Signup(@RequestBody RegisterRequest registerRequest){
        System.out.println(registerRequest.getEmail()+""+registerRequest.getUsername()+" "+registerRequest.getUsername());
        authService.signup(registerRequest);
        return new ResponseEntity<>("user registration successful", HttpStatus.OK);

    }
    @GetMapping("accountverification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token){
        authService.verifyAccount(token);
        return new ResponseEntity<>("account activated successfully",HttpStatus.OK);

    }//fdfd
    @PostMapping("/login")
    public AuthenticationToken login(@RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }

}
