# Appointment Management System

A Spring boot/Vue.js implementation of the codementor.io [Appointment management system](https://www.codementor.io/projects/web/appointment-management-system-compu19a0t)

!!! DISCLAIMER !!!  
This is a demo project that servers only educational purposes. 

The Appointment Management System is a web application that allows companies and service providers to streamline their service appointments. It provides features for users to register and login, view available appointment slots, book appointments, and manage their appointments. This project is built using Spring Boot for the backend and Vue.js for the frontend.

## Technologies Used

- Spring Boot: A Java-based framework for building web applications.
- Vue.js: A JavaScript framework for building user interfaces.
- PostgreSQL: A relational database used for storing application data.
- Liquibase: A database migration tool for managing database schema changes.
- Docker: A containerization platform used to deploy the application as microservices.
- LDAP: Lightweight Directory Access Protocol for user authentication.

## Getting Started

To run the application locally, follow these steps:

1. Clone the repository: `git clone <repository_url>`
2. Install Docker and Docker Compose if not already installed.
3. Set up the PostgreSQL database and update the configuration in `application.properties` file.
4. Build and run the application using Docker Compose: `docker-compose up --build`

## Functionality

- User Registration: Users can register an account with the system.
- User Login: Users can log in to their accounts using LDAP authentication.
- View Available Time Slots: Users can view available time slots for service appointments.
- Book Appointments: Users can book appointments in the upcoming week.
- Manage Appointments: Users can view and edit their appointments (reschedule or cancel).
- Business Admin Dashboard: Businesses can manage available appointment times and view scheduled appointments.

## Remaining Tasks

- Implement email reminders and appointment update notification for customers prior to their appointments.
- Implement a reporting feature for businesses to view scheduled appointments.
- Create a Vue.js frontend application to offer a rich user experience.
- Write comprehensive unit tests and integration tests for the application.
- Implement exception handling and error responses for better error management.
- Add an authorization layer to manage roles and privileges.
- Document the existing code.
- Improve the current open API specification.
