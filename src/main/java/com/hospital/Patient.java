package com.hospital;

import java.util.List;

public class Patient {

    // Fields
    private int patientId;
    private String name;
    private int age;
    private String bloodGroup;
    private String phone;
    private String address;

    // Constructor for add new patient
    public Patient(String name, int age, String bloodGroup, String phone, String address) {

        validateName(name);
        validateAge(age);
        validateBloodGroup(bloodGroup);
        validatePhone(phone);
        validateAddress(address);

        this.name = name.trim();
        this.age = age;
        this.bloodGroup = bloodGroup.trim().toUpperCase();
        this.phone = phone.trim();
        this.address = address.trim();
    }

    // Constructor for fetching patient from database
    public Patient(int patientId, String name, int age, String bloodGroup, String phone, String address) {
        this.patientId = patientId;
        this.name = name.trim();
        this.age = age;
        this.bloodGroup = bloodGroup.trim();
        this.phone = phone.trim();
        this.address = address.trim();
    }

    // Name validator
    private void validateName(String name) {

        if(name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }

        if (name.length() > 100) {
            throw new IllegalArgumentException("Name must be of maximum 100 characters.");
        }

        if (!name.matches("^[a-zA-Z .-]+$")) {
            throw new IllegalArgumentException("Name can only contain letters, spaces, hyphens and dots.");
        }

        if (name.contains("  ")) {
            throw new IllegalArgumentException("Name cannot contains consecutive spaces.");
        }
    }

    // Age validator
    private void validateAge(int age) {
        if (age < 0 || age > 130) {
            throw new IllegalArgumentException("Age must be between 0 and 130.");
        }
    }

    // Blood Group validator
    private void validateBloodGroup(String bloodGroup) {

        if (bloodGroup == null || bloodGroup.isBlank()) {
            throw new IllegalArgumentException("Blood Group cannot be empty.");
        }

        String normalizedBloodGroup = bloodGroup.trim().toUpperCase();     // Convert blood group to uppercase
        List<String> validBloodGroups = List.of("A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-");     // Valid blood groups
        boolean isValid = validBloodGroups.contains(normalizedBloodGroup);     // If blood group valid, then return true

        // Check if blood group valid or not
        if (!isValid) {
          throw new IllegalArgumentException("Invalid blood group. Valid values are: A+, A-, B+, B-, O+, O-, AB+, AB-.");
        }
    }

    // Phone validator
    private void validatePhone(String phone) {

        if (phone == null || phone.isBlank()) {
            throw new IllegalArgumentException("Phone number cannot be empty.");
        }

        if (phone.length() != 10) {
            throw new IllegalArgumentException("Phone number must be exactly 10 digits.");
        }

        if (!phone.matches("^[6-9][0-9]{9}$")) {
            throw new IllegalArgumentException("Phone number only contains digits and starting with 6-9.");
        }
    }

    // Address validator
    private void validateAddress(String address) {

        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("Address cannot be empty.");
        }

        if (address.length() > 255) {
            throw new IllegalArgumentException("Address must be of maximum 255 characters.");
        }
    }

    // Getters
    public int getPatientId() {
        return patientId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getBloodGroup() { return bloodGroup; }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    // Setters
    public void setPatientId(int patientId) {
        if (patientId <= 0) {
            throw new IllegalArgumentException("Patient ID must be a positive number.");
        }
        this.patientId = patientId;
    }

    public void setName(String name) {
        validateName(name);
        this.name = name.trim();
    }

    public void setAge(int age) {
        validateAge(age);
        this.age = age;
    }

    public void setBloodGroup(String bloodGroup) {
        validateBloodGroup(bloodGroup);
        this.bloodGroup = bloodGroup.trim().toUpperCase();
    }

    public void setPhone(String phone) {
        validatePhone(phone);
        this.phone = phone.trim();
    }

    public void setAddress(String address) {
        validateAddress(address);
        this.address = address.trim();
    }

    @Override
    public String toString() {
        return "Patient ID: " + patientId + " | Name: " + name + " | Age: " + age + " | Blood Group: " + bloodGroup;
    }
}
