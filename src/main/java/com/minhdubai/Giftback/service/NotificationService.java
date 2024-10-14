package com.minhdubai.Giftback.service;

import java.util.List;
import java.util.Collections;
import org.springframework.stereotype.Service;

import com.minhdubai.Giftback.domain.dto.common.ResponseDto;
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

   public ResponseDto getNotificationsByUserId(Integer userId) {
      try {
         User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
         List<Notification> notifications = notificationRepository.findByUser(user);
         return ResponseDto.builder()
               .status(200)
               .message("Notifications retrieved successfully")
               .data(notifications)
               .build();
      } catch (Exception e) {
         System.out.println(e.getMessage());
         return ResponseDto.builder()
               .status(500)
               .message("An error occurred while retrieving notifications")
               .data(Collections.emptyList())
               .build();
      }
   }
}
