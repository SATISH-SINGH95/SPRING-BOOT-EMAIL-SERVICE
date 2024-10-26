package com.email.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "email_log")
public class EmailLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "sender_email_id", length = 50, columnDefinition = "character varying(50)")
    private String senderEmailId;

    @Column(name = "sender_name", length = 200, columnDefinition = "character varying(200)")
    private String senderName;

    @Column(name = "subject", length = 500, columnDefinition = "character varying(500)")
    private String subject;

    @Column(name = "content", columnDefinition = "text")
    private String content;

    @Column(name = "error_msg", length = 500, columnDefinition = "character varying(500)")
    private String errorMsg;

    @Column(name = "created_on", nullable = true)
    private LocalDateTime createdOn;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "modified_on", nullable = true)
    private LocalDateTime modifiedOn;

    @Column(name = "modified_by")
    private Integer modifiedBy;

    @Column(name = "has_attachment", columnDefinition = "boolean")
    private Boolean hasAttachment;

    @PrePersist
    protected void onCreate() {
        createdOn = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        modifiedOn = LocalDateTime.now();
    }
}


