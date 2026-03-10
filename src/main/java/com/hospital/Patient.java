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
        validateBloodGroup(bloodGroup);
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
            throw new IllegalArgumentException("Name cannot contains consecutive double places.");
        }
    }

    // Age validator
    private void validateAge(int age) {

        if (age <= 0 || age > 120) {
            throw new IllegalArgumentException("Invalid age.");
        }
    }

    // Blood Group validator
    private void validateBloodGroup(String bloodGroup) {

        if (bloodGroup == null || bloodGroup.isBlank()) {
            throw new IllegalArgumentException("Blood Group cannot be empty.");
        }

        bloodGroup = bloodGroup.toUpperCase().trim();
        List<String> validBloodGroups = List.of("A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-");
        boolean isValid = validBloodGroups.contains(bloodGroup);

        if (!isValid) {
          throw new IllegalArgumentException("Invalid blood group. Valid values are: A+, A-, B+, B-, O+, O-, AB+, AB-.");
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

    public String getBloodGroup() {
        return bloodGroup;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    // Setters
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public void setName(String name) {
        this.name = name.trim();
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup.trim().toUpperCase();
    }

    public void setPhone(String phone) {
        this.phone = phone.trim();
    }

    public void setAddress(String address) {
        this.address = address.trim();
    }

    @Override
    public String toString() {
        return "Patient ID: " + patientId + " | Name: " + name + " | Age: " + age + " | Blood Group: " + bloodGroup;
    }
}
