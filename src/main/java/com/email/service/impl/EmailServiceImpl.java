package com.email.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.email.constants.EmailConstants;
import com.email.exception.EmailException;
import com.email.exception.ExtensionNotValidException;
import com.email.model.EmailRequestObject;
import com.email.service.EmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService{

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(EmailRequestObject emailRequestObject, MultipartFile file) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setTo(emailRequestObject.getTo());
            mimeMessageHelper.setSubject(emailRequestObject.getSubject());
            mimeMessageHelper.setText(emailRequestObject.getMessage(), true);

            if(!file.isEmpty() || file.getSize() != 0){
                validateExtension(file);
                mimeMessageHelper.addAttachment(file.getOriginalFilename(), file);
            }

            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new EmailException(EmailConstants.EMAIL_SENDING_ERROR, HttpStatus.BAD_REQUEST);
        }
    }

    private void validateExtension(MultipartFile file) {
        // get original file name from multipartFile
        String fileName = file.getOriginalFilename();

        String extensionOfFile = fileName.substring(fileName.lastIndexOf('.'));  // will give output as -> .pdf, .docx, .jpeg

        if(!EmailConstants.Extension.EXTENSION_LIST.contains(extensionOfFile.toLowerCase())){
            throw new ExtensionNotValidException(HttpStatus.BAD_REQUEST, EmailConstants.Extension.EXTENSION_NOT_VALID);
        }
    }

}
