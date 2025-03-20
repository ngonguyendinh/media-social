package com.example.mxh.model.notification;

import com.example.mxh.model.user.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user_notification")
public class UserNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(optional = false)
    @JoinColumn(name = "notification_id", nullable = false)
    private Notification notification;


    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    private boolean read = false;
}
