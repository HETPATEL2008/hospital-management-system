package com.hospital;

import org.mindrot.jbcrypt.BCrypt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    // Logger to tracking users operations
    private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);

    // Method for find by username
    public User findByUsername(String username) throws SQLException {

        String findUsername = "SELECT user_id, username, password, role FROM users WHERE username = ?";

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(findUsername)) {

            preparedStatement.setString(1, username);

            try(ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {

                    return new User(
                            resultSet.getInt("user_id"),
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            resultSet.getString("role")
                    );
                }
            }

        } catch (SQLException e) {
            logger.error("Error finding user by username {}: {}", username, e.getMessage());
            throw e;
        }

        return null;
    }

    // Method for save new user
    public boolean save(User user) throws SQLException {

        String saveUser = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(saveUser)) {

            // Hash password before saving
            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, hashedPassword);     // Store hashed password
            preparedStatement.setString(3, user.getRole());

            preparedStatement.executeUpdate();
            logger.info("User {} stored successfully.", user.getUsername());

            return true;

        } catch (SQLException e) {
            logger.error("Error saving user {}: {}", user.getUsername(), e.getMessage());
            throw e;
        }
    }

    // Method for check user exist or not
    public boolean usernameExists(String username) throws SQLException {

        String userExists = "SELECT COUNT(*) FROM users WHERE username = ?";

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(userExists)) {

            preparedStatement.setString(1, username);

            try(ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;     // true if count > 0
                }
            }

        } catch (SQLException e) {
            logger.error("Error checking username {}: {}", username, e.getMessage());
            throw e;
        }

        return false;
    }
}
