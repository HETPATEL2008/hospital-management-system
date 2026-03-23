package com.hospital;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PatientDAO {

    // Logger for tracking patient operations
    private static final Logger logger = LoggerFactory.getLogger(PatientDAO.class);

    // Method for add new patient
    public void addPatient(Patient patient) throws SQLException {

        String savePatient = "INSERT INTO patients (name, age, blood_group, phone, address) VALUES (?, ?, ?, ?, ?)";

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(savePatient, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, patient.getName());
            preparedStatement.setInt(2, patient.getAge());
            preparedStatement.setString(3, patient.getBloodGroup());
            preparedStatement.setString(4, patient.getPhone());
            preparedStatement.setString(5, patient.getAddress());

            preparedStatement.executeUpdate();

            try(ResultSet resultSet = preparedStatement.getGeneratedKeys()) {

                if (resultSet.next()) {
                    patient.setPatientId(resultSet.getInt(1));
                }
            }

            logger.info("Patient {} stored successfully.", patient.getPatientId());

        } catch (SQLException e) {
            logger.error("Error saving patient {}: {}", patient.getName(), e.getMessage());
            throw e;
        }
    }

    // Method for find patient by id
    public Optional<Patient> getPatientById(int patientId) throws SQLException {

        String findPatient = "SELECT patient_id, name, age, blood_group, phone, address FROM patients WHERE patient_id = ?";

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(findPatient)) {

            preparedStatement.setInt(1, patientId);

            try(ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {

                    return Optional.of(new Patient(
                            resultSet.getInt("patient_id"),
                            resultSet.getString("name"),
                            resultSet.getInt("age"),
                            resultSet.getString("blood_group"),
                            resultSet.getString("phone"),
                            resultSet.getString("address")
                    ));
                }
            }

        } catch (SQLException e) {
            logger.error("Error finding patient with id {}: {}", patientId, e.getMessage());
            throw e;
        }

        logger.warn("Patient with id {} not found.", patientId);
        return Optional.empty();
    }

    // Method for find patient by name
    public List<Patient> getPatientByName(String patientName) throws SQLException {

        List<Patient> patients = new ArrayList<>();     // Create an empty List named patients

        String findPatient = "SELECT patient_id, name, age, blood_group, phone, address FROM patients WHERE name = ?";

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(findPatient)) {

            preparedStatement.setString(1, patientName);

            try(ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()){

                    patients.add(new Patient(
                            resultSet.getInt("patient_id"),
                            resultSet.getString("name"),
                            resultSet.getInt("age"),
                            resultSet.getString("blood_group"),
                            resultSet.getString("phone"),
                            resultSet.getString("address")
                    ));
                }
            }

        } catch (SQLException e) {
            logger.error("Error finding patient with name {}: {}", patientName, e.getMessage());
            throw e;
        }

        if (patients.isEmpty()) {
            logger.warn("Patient with name {} not found.", patientName);
        }

        return patients;
    }

    // Method for get all patients
    public List<Patient> getAllPatients() throws SQLException {

        List<Patient> patients = new ArrayList<>();     // Create an empty List named patients

        String allPatients = "SELECT patient_id, name, age, blood_group, phone, address FROM patients";

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(allPatients);
            ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {

                patients.add(new Patient(
                        resultSet.getInt("patient_id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getString("blood_group"),
                        resultSet.getString("phone"),
                        resultSet.getString("address")
                ));
            }

        } catch (SQLException e) {
            logger.error("Error getting all patients: {}", e.getMessage());
            throw e;
        }

        if (patients.isEmpty()) {
            logger.warn("No patients found.");
        }

        return patients;
    }

    // Method for update existing patient
    public void updatePatient(Patient patient) throws SQLException {

        String update = "UPDATE patients SET name = ?, age = ?, blood_group = ?, phone = ?, address = ? WHERE patient_id = ?";

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(update)) {

            preparedStatement.setString(1, patient.getName());
            preparedStatement.setInt(2, patient.getAge());
            preparedStatement.setString(3, patient.getBloodGroup());
            preparedStatement.setString(4, patient.getPhone());
            preparedStatement.setString(5, patient.getAddress());
            preparedStatement.setInt(6, patient.getPatientId());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                logger.info("Patient {} updated successfully.", patient.getPatientId());

            } else {
                logger.warn("Patient {} not found for update.", patient.getPatientId());
            }

        } catch (SQLException e) {
            logger.error("Error updating patient {}: {}", patient.getPatientId(), e.getMessage());
            throw e;
        }
    }

    // Method for delete existing patient
    public void deletePatient(int patientId) throws SQLException {

        String delete = "DELETE FROM patients WHERE patient_id = ?";

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(delete)) {

            preparedStatement.setInt(1, patientId);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                logger.info("Patient {} deleted successfully.", patientId);

            } else {
                logger.warn("Patient {} not found for delete.", patientId);
            }

        } catch (SQLException e) {
            logger.error("Error deleting patient {}: {}", patientId, e.getMessage());
            throw e;
        }
    }

    // Method for check patient exists or not
    public boolean patientExists(int patientId) throws SQLException {

        String checkPatient = "SELECT 1 FROM patients WHERE patient_id = ?";

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(checkPatient)) {

            preparedStatement.setInt(1, patientId);

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }

        } catch (SQLException e) {
            logger.error("Error finding patient {}: {}", patientId, e.getMessage());
            throw e;
        }
    }
}
