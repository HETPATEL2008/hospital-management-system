package com.hospital;

import org.mindrot.jbcrypt.BCrypt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

import java.util.Optional;

public class AuthenticationService {

    // Logger for tracking authentication service
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    // Initialize object of UserDAO class
    private final UserDAO userDAO = new UserDAO();

    // Method for SignUp
    public void signUp(String username, String password, String role) {

        try {
            if (userDAO.usernameExists(username)) {
                logger.warn("Username {} already exists", username);
                System.out.println("Username already taken.");
                return;
            }

            User user = new User(username, password, role);

            userDAO.save(user);
            logger.info("User registered successfully. Username: {}", username);
            System.out.println("User registered successfully.");

        } catch (IllegalArgumentException e) {
            logger.warn("Signup failed — invalid input: {}", e.getMessage());
            System.out.println("Invalid input: " + e.getMessage());

        } catch (SQLException e) {
            logger.error("Signup failed — database error: {}", e.getMessage());
            System.out.println("Database error: " + e.getMessage());
        }
    }

    // Method for Login
    public Optional<User> login(String username, String password) {

        try {
            Optional<User> user = userDAO.findByUsername(username);

            if (user.isEmpty()) {
                logger.warn("Login failed - username not found: {}", username);
                System.out.println("Invalid username or password.");
                return Optional.empty();
            }

            if (!BCrypt.checkpw(password, user.get().getPassword())) {
                logger.warn("Login failed - wrong password for: {}", username);
                System.out.println("Invalid username or password.");
                return Optional.empty();
            }

            logger.info("Login successful for: {}", username);
            return user;

        } catch (SQLException e) {
            logger.error("Login failed - database error: {}", e.getMessage());
            System.out.println("Database error. Please try again.");
            return Optional.empty();
        }
    }
}
