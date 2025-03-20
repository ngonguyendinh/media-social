package com.example.mxh.model.notification;

import lombok.Data;


import java.time.LocalDateTime;
@Data
public class NotificationDto {
    private String message;

    private LocalDateTime createdAt;
    private String type;
}
