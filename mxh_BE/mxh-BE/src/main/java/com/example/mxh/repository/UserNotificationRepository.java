package com.example.mxh.repository;

import com.example.mxh.model.notification.UserNotification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserNotificationRepository extends JpaRepository<UserNotification,Integer> {
}
