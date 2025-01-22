# Employee Management API

## Overview
The **Employee Management API** is a Spring Boot application that 
allows you to manage employees through a set of RESTful endpoints. 
It includes features like employee creation, retrieval, updating, 
and deletion, integrated with third-party services for email validation and 
department verification. The application uses an **H2 in-memory database**
for storing employee data and handles exceptions and logging.
It also sends email notifications on successful employee creation.

### Prerequisites
- Docker and Docker Compose
- Java
- Mvn

## Running the Application Using Docker

### 1. Clone the Repository
Clone the project to your local machine:
```bash
git clone https://github.com/nawafa87/employee-management.git
cd employee-management
```
### 2. Run the maven Command
run the following command to build the project:
```bash
mvn clean package
```
### 3. Build the Docker Image
Create the Docker image for the application:
```bash
docker build -t employee-management .
```
### 4. Run the Docker Image
Run the Docker image for the application:
```bash
docker run -d -p 8080:8080 employee-management
```
The application will now be accessible at http://localhost:8080.
