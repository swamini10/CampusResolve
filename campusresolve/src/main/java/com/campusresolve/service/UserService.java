package com.campusresolve.service;

import java.util.List;

import com.campusresolve.dto.LoginResponse;
import com.campusresolve.dto.RegisterRequest;
import com.campusresolve.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.campusresolve.entity.User;
import com.campusresolve.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    // Register User
    public User registerUser(RegisterRequest request) {

        if (request == null) {
            throw new RuntimeException("Request cannot be null");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setMobile(request.getMobile());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());

        User savedUser = userRepository.save(user);

        // Send registration email
        String subject = "Welcome to CampusResolve";

        String body = "Hello " + user.getFullName() + ",\n\n"
                + "Your CampusResolve account has been successfully created.\n\n"
                + "Registered Email: " + user.getEmail() + "\n"
                + "Role: " + user.getRole() + "\n\n"
                + "You can now login to CampusResolve.\n\n"
                + "Regards,\n"
                + "CampusResolve Team";

        emailService.sendEmail(
                user.getEmail(),
                subject,
                body
        );

        return savedUser;
    }

    // Login User
    public LoginResponse login(String email, String password) {

        if (email == null || email.trim().isEmpty()) {
            throw new RuntimeException("Email is required");
        }

        if (password == null || password.trim().isEmpty()) {
            throw new RuntimeException("Password is required");
        }

        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            throw new RuntimeException("Invalid Email");
        }

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid Password");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        LoginResponse response = new LoginResponse();

        response.setId(user.getId());
        response.setFullName(user.getFullName());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());
        response.setToken(token);
        response.setMessage("Login Successful");

        // Send login notification email
        String subject = "CampusResolve Login Successful";

        String body = "Hello " + user.getFullName() + ",\n\n"
            + "You have successfully logged in to your CampusResolve account.\n\n"
            + "Login Email: " + user.getEmail() + "\n\n"
            + "If this login was not made by you, please contact the CampusResolve administrator.\n\n"
            + "Regards,\n"
            + "CampusResolve Team";

        emailService.sendEmail(user.getEmail(), subject, body);

        return response;
    }

    // Get User By Id
    public User getUserById(Long id) {

        if (id == null) {
            throw new RuntimeException("User Id cannot be null");
        }

        User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        return user;
    }

    // Get All Users
    public List<User> getAllUsers() {

        List<User> users = userRepository.findAll();

        if (users.isEmpty()) {
            throw new RuntimeException("No users found");
        }

        return users;
    }
}