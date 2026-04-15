package com.hospital;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class PatientService {

    // Logger for tracking patient services
    private static final Logger logger = LoggerFactory.getLogger(PatientService.class);

    // initialize object of PatientDAO class
    private static final PatientDAO patientDAO = new PatientDAO();

    // Method for register new patient
    public void registerPatient() throws SQLException {


    }
}