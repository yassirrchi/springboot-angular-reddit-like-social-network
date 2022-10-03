package com.example.socialnet.Controller;

import com.example.socialnet.DataTransferClasses.RegisterRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @PostMapping("/signup")
    public void Signup(@RequestBody RegisterRequest registerRequest){

    }

}
