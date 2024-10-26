package com.email.repository;

import com.email.model.entity.ReceiverDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiverDetailsRepository extends JpaRepository<ReceiverDetails, Long> {
}
