package com.minhdubai.Giftback.domain.dto;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
   
   @CreationTimestamp
   private LocalDateTime createdAt;
   
   @UpdateTimestamp
   private LocalDateTime updatedAt;
}
