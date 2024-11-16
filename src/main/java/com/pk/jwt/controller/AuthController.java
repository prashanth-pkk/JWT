package com.pk.jwt.controller;

import com.pk.jwt.model.JwtRequest;
import com.pk.jwt.service.JwtService;
import com.pk.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;

    public ResponseEntity<String> login(@RequestBody JwtRequest jwtRequest) {
        if (userService.authenticate(jwtRequest.getUsername(), jwtRequest.getPassword())) {
            String token = jwtService.generateToken(jwtRequest.getUsername());
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}
