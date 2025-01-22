package com.example.employee_management.service.impl;

import com.example.employee_management.service.EmailService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Async
    @Override
    public void sendEmail(String to, String subject, String body) {
        // Simulate sending email
        try {
            Thread.sleep(5000);  // Simulate delay in email sending
            System.out.println("Sending email to: " + to);
            System.out.println("Subject: " + subject);
            System.out.println("Body: " + body);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
