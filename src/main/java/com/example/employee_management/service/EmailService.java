package com.example.employee_management.service;

public interface EmailService {
    void sendEmail(String to, String subject, String body);
}
