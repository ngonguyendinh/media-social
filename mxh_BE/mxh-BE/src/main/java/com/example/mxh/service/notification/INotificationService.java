package com.example.mxh.service.notification;

import com.example.mxh.exception.UserException;
import com.example.mxh.model.notification.Notification;
import com.example.mxh.model.notification.NotificationRecipient;
import com.example.mxh.model.user.User;


import java.util.List;
import java.util.Set;

public interface INotificationService {
    Notification createPost(User user, Set<User> followers, String message) throws UserException;
    List<NotificationRecipient> getNotificationsForUser(int userId);
    NotificationRecipient readNotification(Long id);
}
