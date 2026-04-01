# Hospital Management System

A console-based Hospital Management System built 
using Java, JDBC and HikariCP with a MySQL database.

The system allows hospitals to manage patients,
doctors, appointments, medical records, and billing
through a structured layered architecture using DAO
and Service patterns



## Features 

### Authentication

- User Login

- User Signup

- Role-based access control

- Roles supported: ADMIN, RECEPTIONIST


### Patient Management

- Register new patient

- Search patient by ID or name

- View all patients

- Update patient details

- Delete patient


### Doctor Management

- Add new doctor

- View all doctors

- Search doctors by specialization

- Update doctor details

- Delete doctor


### Appointment Management

- Book appointment

- View appointments by patient

- View appointments by doctor

- Cancel appointment

- Reschedule appointment


### Medical Records

- Add diagnosis

- Add prescription

- View patient medical history

- View records by appointment


### Billing System

- Generate bill

- View bill

- Pay bill

- View unpaid bills



## Technologies Used

- Java

- JDBC

- HikariCP

- BCrypt (for secure password hashing)

- MySQL Database

- IntelliJ IDEA



## Database Tables

- users

- patients 

- doctors

- appointments

- medical_records

- bills



## Build and Run

This project using **Maven** for dependency
management.



## License

MIT License — see [LICENSE](./LICENSE)
