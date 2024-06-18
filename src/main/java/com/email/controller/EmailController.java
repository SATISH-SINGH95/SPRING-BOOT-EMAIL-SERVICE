package com.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.email.model.EmailRequestObject;
import com.email.service.EmailService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<?> sendEmailInBulk(@RequestPart @Valid EmailRequestObject emailRequesObject, @RequestPart @Valid MultipartFile file){
        emailService.sendEmail(emailRequesObject, file);
        return new ResponseEntity<>("Email sent successfully", HttpStatus.OK);

    }

}


