package com.email.controller;

import com.email.builder.ApiResponseBuilder;
import com.email.builder.dto.ApiResponse;
import com.email.constant.ApiConstants;
import com.email.exception.BadApiRequestException;
import com.email.model.request.EmailRequestObject;
import com.email.service.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(ApiConstants.API_VERSION + ApiConstants.EMAIL)
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;
    private final ObjectMapper objectMapper;


    /***
     * API to Send Email
     * @param emailRequestObject
     * @param file
     * @return emailResponse
     * @Author Satish
     * **/
    @PostMapping(ApiConstants.EP_SEND_EMAIL)
    @Operation(summary = "", description = "")
    public ResponseEntity<ApiResponse> sendEmail(
            @RequestParam String emailRequestObject,
            @RequestParam(required = false) MultipartFile file) {

        EmailRequestObject emailRequestObject1 = null;
        try {
            emailRequestObject1 = objectMapper.readValue(emailRequestObject, EmailRequestObject.class);
            if(emailRequestObject1.getTo() == null || emailRequestObject1.getTo().isEmpty()) {
                throw new RuntimeException("To field can not be null or empty");
            }
            String emailResponse = emailService.sendEmail(emailRequestObject1, file);
            return ApiResponseBuilder.getSuccessResponse(emailResponse, "Email sent successfully", HttpStatus.OK);
        } catch (BadApiRequestException ex) {
            throw ex;
        } catch (Exception ex) {
            return ApiResponseBuilder.getErrorResponse(null, ex.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }
}
