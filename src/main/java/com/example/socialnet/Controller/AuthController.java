package com.example.socialnet.Controller;

import com.example.socialnet.DataTransferClasses.RegisterRequest;
import com.example.socialnet.Service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

}
