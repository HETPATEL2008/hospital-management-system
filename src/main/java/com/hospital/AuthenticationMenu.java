package com.hospital;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.Scanner;

public class AuthenticationMenu {

    // Logger for tracking authentication menu
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationMenu.class);

    // Initializes object of AuthenticationService
    private final AuthenticationService authenticationService = new AuthenticationService();

    // Scanner for taking user input
    private final Scanner scanner = new Scanner(System.in);

    public Optional<User> authMenu() {

        while (true) {

            System.out.println("===== Hospital Management System =====");
            System.out.println("1. Login");
            System.out.println("2. Sign Up");
            System.out.println("0. Exit\n");

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {

                // For Login
                case "1" -> {
                    System.out.print("Enter Username: ");
                    String username = scanner.nextLine().trim().toLowerCase();

                    System.out.print("Enter Password: ");
                    String password = scanner.nextLine().trim();

                    Optional<User> user = authenticationService.login(username, password);

                    if (user.isPresent())
                        return user;
                }

                // For SignUp
                case "2" -> {
                    System.out.print("Enter Username: ");
                    String username = scanner.nextLine().trim().toLowerCase();

                    System.out.print("Enter Password: ");
                    String password = scanner.nextLine().trim();

                    System.out.print("Enter Role: ");
                    String role = scanner.nextLine().trim().toUpperCase();

                    authenticationService.signUp(username, password, role);
                }

                // For Exit
                case "0" -> {
                    System.out.println("Thank You!");
                    return Optional.empty();
                }

                default -> {
                    logger.warn("Invalid menu choice: {}", choice);
                    System.out.println("Invalid choice! Try again.");
                }
            }
        }
    }
}
