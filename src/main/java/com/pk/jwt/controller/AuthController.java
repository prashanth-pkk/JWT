package com.pk.jwt.controller;

import com.pk.jwt.model.User;
import com.pk.jwt.repository.UserRepository;
import com.pk.jwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        User user = userRepository.findByUsername(username);

        if (user != null && password.equals(user.getPassword())) { // Normally, use BCrypt for password comparison
            return JwtUtil.generateToken(username);
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }
}
