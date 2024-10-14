package com.minhdubai.Giftback.controller;

import com.minhdubai.Giftback.service.NotificationService;
import com.minhdubai.Giftback.domain.dto.common.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<Boolean> addNotificationToUser(@PathVariable Integer userId, @RequestBody String message) {
        boolean result = notificationService.addNotificaitonToUser(userId, message);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{notificationId}")
    public ResponseEntity<Boolean> seenNotification(@PathVariable Integer notificationId) {
        boolean result = notificationService.seenNotification(notificationId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ResponseDto> getNotificationsByUserId(@PathVariable Integer userId) {
        ResponseDto notifications = notificationService.getNotificationsByUserId(userId);
        return ResponseEntity.ok(notifications);
    }
}
