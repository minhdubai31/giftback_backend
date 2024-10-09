package com.minhdubai.Giftback.domain.dto.notification;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.minhdubai.Giftback.domain.dto.user.UserDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {
   private Integer id;
   private String message;

   @Builder.Default
   private Boolean is_read = false;

   @JsonBackReference
   @EqualsAndHashCode.Exclude
   private UserDto user;
   private LocalDateTime createdAt;
   private LocalDateTime updatedAt; 
}
