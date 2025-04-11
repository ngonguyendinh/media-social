package com.example.mxh.map;

import com.example.mxh.model.notification.Notification;
import com.example.mxh.model.notification.NotificationDto;
import com.example.mxh.model.notification.NotificationRecipient;

public class NotificationMapper {
    public static NotificationDto map(NotificationRecipient notificationRecipient){
        NotificationDto dto = new NotificationDto();
        dto.setNotification(notificationRecipient.getNotification().getMessage());
        dto.setReceivedAt(notificationRecipient.getReceivedAt());
        return dto;
    }
}