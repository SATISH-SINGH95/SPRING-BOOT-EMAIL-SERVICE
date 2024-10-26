package com.email.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "receiver_details")
public class ReceiverDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "email_log_id", referencedColumnName = "id")
    private EmailLog emailLog;

    @Column(name = "send_type", length = 100, columnDefinition = "character varying(100)")
    private String sendType;

    @Column(name = "receiver_email_id", length = 50, columnDefinition = "character varying(50)")
    private String receiverEmailId;

    @Column(name = "created_on", nullable = true)
    private LocalDateTime createdOn;

    @Column(name = "created_by")
    private Integer createdBy;

}
