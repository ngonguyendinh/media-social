package com.example.mxh.controller;

import com.example.mxh.exception.UserException;
import com.example.mxh.map.NotificationMapper;
import com.example.mxh.model.notification.Notification;
import com.example.mxh.model.notification.NotificationDto;
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



    // Lấy danh sách thông báo của người dùng hiện tại
    @GetMapping
    public ResponseEntity<List<NotificationDto>> getUserNotifications(@RequestHeader("Authorization") String jwt) throws UserException {
        // Giả sử phương thức này giải mã JWT và lấy user
        User user = userService.findUserByJwt(jwt);
        List<Notification> notifications = notificationService.getNotificationsForUser(user.getId());
        // Chuyển đổi entity Notification sang DTO để trả về
        List<NotificationDto> dtos = notifications.stream()
                .map(NotificationMapper::map)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // Đánh dấu thông báo đã đọc (ví dụ)
//    @PutMapping("/{id}/read")
//    public ResponseEntity<Void> markNotificationAsRead(@PathVariable("id") Long notificationId,
//                                                       @RequestHeader("Authorization") String jwt) throws UserException {
//        User user = userService.findUserByJwt(jwt);
//        notificationService.markAsRead(user.getId(), notificationId);
//        return ResponseEntity.ok().build();
//    }
}
