package com.minhdubai.Giftback.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;

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

   public boolean addNotificationToUser(Integer userId, String message) {
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
         notification.setIsRead(true);
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

   public ResponseDto sendNotificationToAllUsers(String message) {
      try {
         List<User> users = userRepository.findAll();
         Integer randomInteger = (int) (Math.random() * Short.MAX_VALUE);
         LocalDateTime currentTime = LocalDateTime.now();
         for (User user : users) {
            Notification notification = Notification.builder()
                  .message(message)
                  .user(user)
                  .messageGroup(randomInteger)
                  .createdAt(currentTime)
                  .build();
            notificationRepository.save(notification);
         }
         return ResponseDto.builder()
               .status(200)
               .message("Notifications sent to all users successfully")
               .data(null)
               .build();
      } catch (Exception e) {
         System.out.println(e.getMessage());
         return ResponseDto.builder()
               .status(500)
               .message("An error occurred while sending notifications")
               .data(null)
               .build();
      }
   }

   public ResponseDto getAllNotificationsGroupedByMessageGroup() {
      try {
         List<Notification> notifications = notificationRepository.findAll();
         List<Map<String, Object>> groupedNotifications = notifications.stream()
               .collect(Collectors.groupingBy(Notification::getMessageGroup))
               .values().stream()
               .map(group -> {
                   Notification firstNotification = group.get(0);
                   long seenCount = group.stream().filter(notification -> notification.getIsRead()).count();
                   Map<String, Object> notificationDetails = new HashMap<>();
                   notificationDetails.put("message", firstNotification.getMessage());
                   notificationDetails.put("createdAt", firstNotification.getCreatedAt());
                   notificationDetails.put("seen", seenCount);
                   return notificationDetails;
               })
               .collect(Collectors.toList());

         return ResponseDto.builder()
               .status(200)
               .message("Notifications grouped by message group retrieved successfully")
               .data(groupedNotifications)
               .build();
      } catch (Exception e) {
         System.out.println(e.getMessage());
         return ResponseDto.builder()
               .status(500)
               .message("An error occurred while retrieving grouped notifications")
               .data(Collections.emptyMap())
               .build();
      }
   }
}
