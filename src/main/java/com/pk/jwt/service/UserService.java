package com.pk.jwt.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public boolean authenticate(String username, String password) {
        return "user".equals(username) && "password".equals(password);
    }
}
