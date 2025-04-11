package com.example.mxh.service.notification;

import com.example.mxh.exception.UserException;
import com.example.mxh.model.notification.Notification;
import com.example.mxh.model.notification.NotificationRecipient;
import com.example.mxh.model.notification.NotificationTask;
import com.example.mxh.model.user.User;
import com.example.mxh.repository.NotificationRecipientRepository;
import com.example.mxh.repository.NotificationRepository;
import com.example.mxh.service.user.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;

import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

@Service
@AllArgsConstructor
public class NotificationService implements  INotificationService{

    private NotificationRepository notificationRepository;
    private NotificationRecipientRepository notificationRecipientRepository;
    private NotificationWorkQueu notificationWorkQueu;

    private IUserService userService;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    public Notification create( User user ,Set<User> followers, String message) throws UserException {
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setUser(user);
        notification.setType("newPost");
        notificationRepository.save(notification);

        for(User follower : followers){
            NotificationRecipient recipient = new NotificationRecipient();
            recipient.setNotification(notification);
            recipient.setRecipient(follower);
            recipient.setIsRead(false);
            recipient.setReceivedAt(LocalDateTime.now());
            NotificationRecipient savedRecipient = notificationRecipientRepository.save(recipient);

            // Tạo nhiệm vụ gửi thông báo
            NotificationTask task = new NotificationTask(
                    savedRecipient.getId(),
                    (long) follower.getId(),
                    message
            );
            notificationWorkQueu.enqueue(task);
        }
        return notificationRepository.save(notification);
    }

    @Override
    public List<NotificationRecipient> getNotificationsForUser(int userId) {
        return notificationRecipientRepository.findByRecipientIdAndIsReadFalse(userId);
    }

    @Override
    public NotificationRecipient readNotification(Long id) {
        NotificationRecipient notificationRecipient = notificationRecipientRepository.findById(id).get();
        notificationRecipient.setIsRead(true);
        return notificationRecipientRepository.save(notificationRecipient);
    }

}
