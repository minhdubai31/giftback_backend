package com.minhdubai.Giftback.service;

import java.util.List;
import java.util.Collections;
import org.springframework.stereotype.Service;

import com.minhdubai.Giftback.domain.entity.Notification;
import com.minhdubai.Giftback.domain.entity.User;
import com.minhdubai.Giftback.repository.NotificationRepository;
import com.minhdubai.Giftback.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService {
   private final UserRepository userRepository;
   private final NotificationRepository notificationRepository;

   public boolean addNotificaitonToUser(Integer userId, String message) {
      try {
         User user = userRepository.findById(userId).get();
         Notification notification = Notification.builder()
            .message(message)
            .user(user)
            .build();
         
         notificationRepository.save(notification);
         return true;
      } catch (Exception e) {
         System.out.println(e.getMessage());
         return false;
      }
   }

   public boolean seenNotification(Integer notificationId) {
      try {
         Notification notification = notificationRepository.findById(notificationId).get();
         notification.setIs_read(true);
         notificationRepository.save(notification);
         return true;
      } catch (Exception e) {
         System.out.println(e.getMessage());
         return false;
      }
   }

   public List<Notification> getNotificationsByUserId(Integer userId) {
      try {
         User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
         return notificationRepository.findByUser(user);
      } catch (Exception e) {
         System.out.println(e.getMessage());
         return Collections.emptyList();
      }
   }
}
