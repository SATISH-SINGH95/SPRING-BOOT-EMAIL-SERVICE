package com.email.model.request;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailLogRequestObject {

    private String senderEmailId;
    private String senderName;
    private String subject;
    private String content;
    private String errorMsg;
    private LocalDateTime createdOn;
    private Integer createdBy;
    private LocalDateTime modifiedOn;
    private Integer modifiedBy;
    private Boolean hasAttachment;
}
