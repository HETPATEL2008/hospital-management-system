package com.hospital;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class Main {

    // Logger for tracking application operations
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    // Main method
    public static void main(String[] args) {

        // Initialize object of AuthenticationMenu class
        AuthenticationMenu authenticationMenu = new AuthenticationMenu();

        // Start authentication and get logged-in user
        Optional<User> loggedInUser = authenticationMenu.authMenu();

        // Check if user logged-in successfully or not
        if (loggedInUser.isPresent()) {
            logger.info("User {} logged in. Redirecting to MainMenu.", loggedInUser.get().getUsername());
            System.out.println("Login successful - MainMenu coming soon");

        } else {
            logger.info("Application exited by user.");
            System.out.println("Exiting application.");
            DatabaseConnection.closePool();     // Close the HikariCP connection pool
        }
    }
}
