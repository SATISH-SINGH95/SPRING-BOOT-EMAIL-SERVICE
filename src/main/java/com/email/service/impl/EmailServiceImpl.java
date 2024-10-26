package com.email.service.impl;

import com.email.exception.EmailException;
import com.email.model.entity.EmailLog;
import com.email.model.entity.ReceiverDetails;
import com.email.model.request.EmailLogRequestObject;
import com.email.model.request.EmailRequestObject;
import com.email.repository.EmailLogRepository;
import com.email.repository.ReceiverDetailsRepository;
import com.email.service.EmailService;
import com.email.validator.CustomValidator;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    private static final String SEND_TYPE_TO = "To";
    private static final String SEND_TYPE_CC = "Cc";
    private static final String SEND_TYPE_BCC = "Bcc";


    private final EmailLogRepository emailLogRepository;
    private final ReceiverDetailsRepository receiverDetailsRepository;
    private final ModelMapper modelMapper;
    private final JavaMailSender javaMailSender;
    private CustomValidator customValidator;

    @Value("${email.sender.email}")
    private String senderEmail;
    @Value("${email.sender.name}")
    private String senderName;

    @Override
    public String sendEmail(EmailRequestObject emailRequestObject, MultipartFile file) {

        // if(emailRequestObject != null){
        //     customValidator.validate(emailRequestObject);
        // }
        // assert emailRequestObject != null;
        EmailLogRequestObject emailLogDto = prepareEmailLog(emailRequestObject);
        boolean isFileExist = (file != null || !file.isEmpty());
        if(isFileExist){
            emailLogDto.setHasAttachment(!file.isEmpty());
        }
        try {
            triggerEmail(emailRequestObject, file, isFileExist);
            EmailLog emailLog = logSuccessEmail(emailLogDto);
            logReceiverDetails(emailRequestObject, emailLog);
        } catch (Exception e) {
            log.error("Email Failure: {}", e.getMessage());
            String message = "Email has not been sent";
            logFailureEmail(emailLogDto, message);
            throw new EmailException(message);
        }
        return "Email has been sent";
    }

    private void logFailureEmail(EmailLogRequestObject emailLogDto, String errorMsg) {
        emailLogDto.setErrorMsg(errorMsg);
        EmailLog emailLog = modelMapper.map(emailLogDto, EmailLog.class);
        emailLogRepository.save(emailLog);
    }

    private void logReceiverDetails(EmailRequestObject emailRequestObject, EmailLog emailLog) {
        saveReceiverDetails(emailRequestObject.getTo(), SEND_TYPE_TO, emailLog);
        saveReceiverDetails(emailRequestObject.getCc(), SEND_TYPE_CC, emailLog);
        saveReceiverDetails(emailRequestObject.getBcc(), SEND_TYPE_BCC, emailLog);
    }

    private void saveReceiverDetails(List<String> emails, String sendType, EmailLog emailLog) {
        if (emails == null || emails.isEmpty()) return;

        List<ReceiverDetails> receiverDetailsList = emails.stream()
                .map(email -> buildReceiverDetails(email, sendType, emailLog))
                .toList();

        receiverDetailsRepository.saveAll(receiverDetailsList);
    }

    private ReceiverDetails buildReceiverDetails(String email, String sendType, EmailLog emailLog) {
        ReceiverDetails receiverDetails = new ReceiverDetails();
        receiverDetails.setEmailLog(emailLog);
        receiverDetails.setSendType(sendType);
        receiverDetails.setReceiverEmailId(email);
        receiverDetails.setCreatedBy(1);
        receiverDetails.setCreatedOn(LocalDateTime.now());
        return receiverDetails;
    }

    private EmailLog logSuccessEmail(EmailLogRequestObject emailLogDto) {
        EmailLog emailLog = modelMapper.map(emailLogDto, EmailLog.class);
        return emailLogRepository.save(emailLog);
    }

    private void triggerEmail(EmailRequestObject emailRequestObject, MultipartFile file, boolean isFileExist) throws MessagingException, UnsupportedEncodingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom(new InternetAddress(senderEmail, senderName));
        mimeMessageHelper.setTo(emailRequestObject.getTo().toArray(new String[0]));
        if (emailRequestObject.getCc() != null && !emailRequestObject.getCc().isEmpty()) {
            mimeMessageHelper.setCc(emailRequestObject.getCc().toArray(new String[0]));
        }
        if (emailRequestObject.getBcc() != null && !emailRequestObject.getBcc().isEmpty()) {
            mimeMessageHelper.setBcc(emailRequestObject.getBcc().toArray(new String[0]));
        }
        mimeMessageHelper.setSubject(emailRequestObject.getSubject());
        mimeMessageHelper.setText(emailRequestObject.getMessage(), true);
        if(isFileExist){
            mimeMessageHelper.addAttachment(Objects.requireNonNull(file.getOriginalFilename()), file);
        }
        javaMailSender.send(mimeMessage);
    }

    private EmailLogRequestObject prepareEmailLog(EmailRequestObject emailRequestObject) {
        EmailLogRequestObject emailLogDto = new EmailLogRequestObject();
        emailLogDto.setSenderEmailId(senderEmail);
        emailLogDto.setSenderName(senderName);
        emailLogDto.setSubject(emailRequestObject.getSubject());
        emailLogDto.setContent(emailRequestObject.getMessage());
        emailLogDto.setCreatedBy(1);  // Assuming created_by user is 1
        return emailLogDto;
    }
}
