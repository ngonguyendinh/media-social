package com.example.mxh.controller;

import com.example.mxh.exception.UserException;
import com.example.mxh.map.NotificationMapper;
import com.example.mxh.map.NotificationRecipientMapper;
import com.example.mxh.model.notification.Notification;
import com.example.mxh.model.notification.NotificationDto;
import com.example.mxh.model.notification.NotificationRecipient;
import com.example.mxh.model.notification.NotificationRecipientDto;
import com.example.mxh.model.user.User;
import com.example.mxh.service.notification.INotificationService;
import com.example.mxh.service.user.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notifications")
@AllArgsConstructor
public class NotificationController {

    private final INotificationService notificationService;
    private final IUserService userService;



    @GetMapping
    public ResponseEntity<List<NotificationRecipientDto>> getUserNotifications(@RequestHeader("Authorization") String jwt) throws UserException {
        User user = userService.findUserByJwt(jwt);
        List<NotificationRecipientDto> notifications = NotificationRecipientMapper.map(notificationService.getNotificationsForUser(user.getId()));
        return ResponseEntity.ok(notifications);
    }
    @PutMapping("/read/{id}")
    public ResponseEntity<NotificationDto> readNotification(@PathVariable("id")Long id){
        NotificationDto dto = NotificationMapper.map(notificationService.readNotification(id));
        return ResponseEntity.ok(dto);
    }

}
