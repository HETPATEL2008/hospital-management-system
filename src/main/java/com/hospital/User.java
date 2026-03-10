package com.hospital;

public class User {

    // Fields
    private int userId;
    private String username;
    private String password;
    private String role;  // ADMIN or RECEPTIONIST

    // Constructor for register new user
    public User(String username, String password, String role) {
        validateUserName(username);
        validatePassword(password);
        validateRole(role);

        this.username = username.trim();
        this.password = password;
        this.role = role;
    }

    // Constructor for fetching data from database
    public User(int userId, String username, String password, String role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
    }


    // Username validator
    private void validateUserName(String username) {

        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be empty.");
        }

        if (username.trim().length() < 3 || username.trim().length() > 20) {
            throw new IllegalArgumentException("Username must be between 3 and 20 characters.");
        }

        if (username.contains(" ")) {
            throw new IllegalArgumentException("Username cannot contain spaces.");
        }

        if (!username.matches("^[a-zA-Z0-9_]+$")) {
            throw new IllegalArgumentException("Username can only contain letters, numbers and underscore.");
        }

        if (!Character.isLetter(username.charAt(0))) {
            throw new IllegalArgumentException("Username must start with a letter.");
        }
    }

    // Password validator
    private void validatePassword(String password) {

        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be empty.");
        }

        if (password.length() < 8 || password.length() > 30) {
            throw new IllegalArgumentException("Password must be between 8 and 30 characters.");
        }

        if (password.contains(" ")) {
            throw new IllegalArgumentException("Password cannot contain spaces.");
        }

        // At least one upper case letter
        if (!password.matches(".*[A-Z].*")) {
            throw new IllegalArgumentException("Password must contain at least one uppercase letter.");
        }

        // At least one lower case letter
        if (!password.matches(".*[a-z].*")) {
            throw new IllegalArgumentException("Password must contain at least one lowercase letter.");
        }

        // At least one digit
        if (!password.matches(".*[0-9].*")) {
            throw new IllegalArgumentException("Password must contain at least one number.");
        }

        // at least one special character
        if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{}].*")) {
            throw new IllegalArgumentException("Password must contain at least one special character.");
        }
    }

    // Role validator
    private void validateRole(String role) {

        if (role == null || role.isBlank()) {
            throw new IllegalArgumentException("Role cannot be empty.");
        }

        if (!role.equals("ADMIN") && !role.equals("RECEPTIONIST")) {
            throw new IllegalArgumentException("Role must be ADMIN or RECEPTIONIST.");
        }
    }

    // Getters
    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    // Setters
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        validateUserName(username);
        this.username = username.trim();
    }

    public void setPassword(String password) {
        validatePassword(password);
        this.password = password;
    }

    public void setRole(String role) {
        validateRole(role);
        this.role = role;
    }

    @Override
    public String toString() {
        return "User ID: " + userId + " | Username: " + username + " | Role " + role;
    }
}
