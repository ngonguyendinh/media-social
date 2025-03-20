package com.example.mxh.service.notification;

import com.example.mxh.model.notification.Notification;
import com.example.mxh.model.user.User;

import java.util.List;

public interface INotificationService {
    Notification create(Notification notification);
    List<Notification> getNotificationsForUser(int userId);
}
