package com.hospital;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.pool.HikariPool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnection {

    // Logger for tracking database operations
    private static final Logger logger = LoggerFactory.getLogger(DatabaseConnection.class);

    // Declare Hikari Data Source
    private static final HikariDataSource hikariDataSource;

    // Static block for Initialize HikariCP pool
    static {
        try {
            HikariConfig hikariConfig = new HikariConfig();

            String dbUser = System.getenv("DB_USER");
            String dbPassword = System.getenv("DB_PASSWORD");

            if (dbUser == null || dbPassword == null) {
                logger.error("Environment variables DB_USER or DB_PASSWORD are not set.");
                throw new IllegalStateException("DB_USER and DB_PASSWORD must be set.");
            }

            hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/hospital");
            hikariConfig.setUsername(dbUser);
            hikariConfig.setPassword(dbPassword);

            hikariConfig.setMaximumPoolSize(10);
            hikariConfig.setMinimumIdle(5);
            hikariConfig.setMaxLifetime(1800000);
            hikariConfig.setIdleTimeout(600000);
            hikariConfig.setConnectionTimeout(30000);
            hikariConfig.setLeakDetectionThreshold(2000);

            hikariDataSource = new HikariDataSource(hikariConfig);
            logger.info("HikariCP pool initialized successfully.");

        } catch (IllegalStateException e) {
            logger.error("Environment setup error: {}", e.getMessage());
            throw e;

        } catch (IllegalArgumentException e) {
            logger.error("Invalid HikariCP configuration: {}", e.getMessage());
            throw e;

        } catch (HikariPool.PoolInitializationException e) {
            logger.error("Failed to initialize HikariCP pool: {}", e.getMessage());
            throw e;
        }
    }

    // Method for connect with database
    public static Connection getConnection() throws SQLException {
        try {
            return hikariDataSource.getConnection();
        } catch (SQLException e) {
            logger.error("Failed to get connection from pool: {}", e.getMessage());
            throw e;
        }
    }

    // Method for close HikariCP pool
    public static void closePool() {
        if (hikariDataSource != null && !hikariDataSource.isClosed()) {
            hikariDataSource.close();
            logger.info("HikariCP pool closed.");
        }
    }
}
