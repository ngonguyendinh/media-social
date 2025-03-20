package com.example.mxh.service.notification;

import com.example.mxh.model.notification.Notification;
import com.example.mxh.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NotificationService implements  INotificationService{
    private NotificationRepository notificationRepository;
    @Override
    public Notification create(Notification notification) {

        return notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getNotificationsForUser(int userId) {
        return notificationRepository.findByUserUserId(userId);

    }

}
