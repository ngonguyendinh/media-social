package com.example.mxh.repository;

import com.example.mxh.model.notification.NotificationRecipient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRecipientRepository extends JpaRepository<NotificationRecipient, Long> {
    List<NotificationRecipient> findByRecipientIdAndIsReadFalse(int recipientId);
}
