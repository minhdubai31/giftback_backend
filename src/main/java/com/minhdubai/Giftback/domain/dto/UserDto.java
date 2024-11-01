package com.minhdubai.Giftback.domain.dto;

import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.minhdubai.Giftback.domain.constant.AuthProvider;
import com.minhdubai.Giftback.domain.constant.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
   private Integer id;
   private String username;
   private String name;

   @Builder.Default
   private Role role = Role.USER;
   
   @Builder.Default
   private AuthProvider authProvider = AuthProvider.LOCAL;

   @JsonBackReference
   private GroupDto group;
   
   @JsonManagedReference
   private Set<NotificationDto> notifications;

   @JsonManagedReference
   private WalletDto wallet;
   
   @CreationTimestamp
   private LocalDateTime createdAt;
   
   @UpdateTimestamp
   private LocalDateTime updatedAt;
}
