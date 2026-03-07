package com.hospital;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

import java.util.Scanner;

public class AuthenticationService {

    // Logger for tracking authentication services
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    // Scanner for take input
    private static final Scanner scanner = new Scanner(System.in);

    // Initialize object of UserDAO class
    private static final UserDAO userDAO = new UserDAO();

    // Method for SignUp
    public void signUp() {

        try {
            System.out.print("Enter Username: ");
            String username = scanner.nextLine().trim();

            System.out.print("Enter Password: ");
            String password = scanner.nextLine().trim();

            System.out.print("Enter Role: ");
            String role = scanner.nextLine().trim().toUpperCase();

            // Check if username exists or not
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
    public User login() {

        try {
            System.out.print("Enter Username: ");
            String username = scanner.nextLine().trim();

            System.out.print("Enter Password: ");
            String password = scanner.nextLine().trim();

            // Find user by username
            User user = userDAO.findByUsername(username);

            // Check if user exists or not
            if (user == null) {
                logger.warn("Login failed - username not found: {}", username);
                System.out.println("Invalid username or password.");
                return null;
            }

            // Check if plain password and hashed password matches
            if (!BCrypt.checkpw(password, user.getPassword())) {
                logger.warn("Login failed - wrong password for: {}", username);
                System.out.println("Invalid username or password.");
                return null;
            }

            logger.info("Login successful for: {}", username);
            System.out.println("Welcome, " + username + " | Role: " +  user.getRole());
            return user;

        } catch (SQLException e) {
            logger.error("Login failed - database error: {}", e.getMessage());
            System.out.println("Database error. Please try again.");
            return null;
        }
    }
}
