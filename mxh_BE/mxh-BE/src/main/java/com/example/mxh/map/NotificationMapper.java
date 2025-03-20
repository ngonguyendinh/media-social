package com.example.mxh.map;

import com.example.mxh.model.notification.Notification;
import com.example.mxh.model.notification.NotificationDto;

public class NotificationMapper {
    public static NotificationDto map(Notification notification){
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setMessage(notification.getMessage());
        notificationDto.setType(notification.getType());
        notificationDto.setCreatedAt(notification.getCreatedAt());
        return notificationDto;
    }
}
