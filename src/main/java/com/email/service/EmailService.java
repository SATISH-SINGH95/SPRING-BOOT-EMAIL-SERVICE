package com.email.service;

import com.email.model.request.EmailRequestObject;
import org.springframework.web.multipart.MultipartFile;

public interface EmailService {

    /***
     * Method to Send Email
     * @param emailRequestObject
     * @return String
     * @Author Satish
     * **/
    String sendEmail(EmailRequestObject emailRequestObject, MultipartFile file);

}
