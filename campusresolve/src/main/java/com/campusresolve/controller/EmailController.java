package com.campusresolve.controller;


import com.campusresolve.service.EmailService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public String sendEmail(
            @RequestParam String to,
            @RequestParam String subject,
            @RequestParam String body) {

                System.out.println("EMAIL CONTROLLER CALLED");

        emailService.sendEmail(to, subject, body);

        return "Email sent successfully!";
    }
}
