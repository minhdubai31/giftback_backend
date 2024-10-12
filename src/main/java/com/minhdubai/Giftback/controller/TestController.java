package com.minhdubai.Giftback.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.minhdubai.Giftback.domain.dto.UserDto;
import com.minhdubai.Giftback.service.NotificationService;
import com.minhdubai.Giftback.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/test")
@AllArgsConstructor
public class TestController {
   private NotificationService notificationService;
   private UserService userService;

   @PostMapping(path = "")
   public ResponseEntity<UserDto> testing(@RequestParam Integer userId, @RequestParam String message) {
      boolean success = notificationService.addNotificaitonToUser(userId, message);
      if (success) {
         UserDto response = userService.findById(userId).orElse(null);
         return ResponseEntity.ok(response);
      }
      return null;
   }
}
