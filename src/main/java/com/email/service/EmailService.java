package com.email.service;

import org.springframework.web.multipart.MultipartFile;

import com.email.model.EmailRequestObject;

public interface EmailService {
    void sendEmail(EmailRequestObject emailRequestObject, MultipartFile file);
}
