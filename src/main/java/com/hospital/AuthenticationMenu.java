package com.hospital;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class AuthenticationMenu {

    // Logger for tracking authentication operations
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationMenu.class);

    // Initialize object of AuthenticationService class
    private static final AuthenticationService authenticationService = new AuthenticationService();

    // Scanner for take input
    private static final Scanner scanner = new Scanner(System.in);

    // Method for authentication
    public User authMenu() {

        while (true) {

            System.out.println("===== Hospital Management System =====");
            System.out.println("1. Login");
            System.out.println("2. Sign Up");
            System.out.println("0. Exit\n");

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {

                // Login
                case "1" -> {
                    User user = authenticationService.login();

                    if (user != null) {
                        return user;     // Login success return to main
                    }
                }

                // Sign Up
                case "2" -> authenticationService.signUp();

                // Exit
                case "0" -> {
                    logger.info("Application exited by user.");
                    System.out.println("Thank You!");
                    return null;
                }

                default -> {
                    logger.warn("Invalid menu choice: {}", choice);
                    System.out.println("Invalid choice! Try again.");
                }
            }
        }
    }
}
