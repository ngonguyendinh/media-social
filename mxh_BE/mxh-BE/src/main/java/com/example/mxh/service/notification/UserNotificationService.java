package com.example.mxh.service.notification;

import com.example.mxh.model.notification.UserNotification;
import com.example.mxh.repository.UserNotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserNotificationService implements IUserNotificationService{
    private UserNotificationRepository userNotificationRepository;
    @Override
    public UserNotification create(UserNotification userNotification) {
        return userNotificationRepository.save(userNotification);
    }
}
