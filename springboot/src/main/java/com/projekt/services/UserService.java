package com.projekt.services;

import com.projekt.dao.*;
import com.projekt.dto.RegistrationRequest;
import com.projekt.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// ...

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void registerUser(RegistrationRequest registrationRequest) {
        User user = new User();
        user.setUsername(registrationRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));

        // Set default role (you can customize this logic)
        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("Role not found: USER"));
        user.setRoles(Set.of());  // Set the roles here

        userRepository.save(user);
    }
}
