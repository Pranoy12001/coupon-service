package com.pranoy.spring.security.controllers;

import com.pranoy.spring.security.model.User;
import com.pranoy.spring.security.repos.UserRepository;
import com.pranoy.spring.security.security.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    SecurityService securityService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/showReg")
    public String showRegistrationPage() {
        return "registerUser";
    }

    @PostMapping("/registerUser")
    public String register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "login";
    }

    @GetMapping("/")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(String email, String password) {
        boolean loginResponse = securityService.login(email, password);

        if (loginResponse) {
            return "index";
        }

        return "login";
    }
}
