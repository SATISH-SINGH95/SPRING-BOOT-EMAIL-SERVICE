package com.email.repository;

import com.email.model.entity.EmailLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailLogRepository extends JpaRepository<EmailLog, Long> {
}
