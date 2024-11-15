package com.minhdubai.Giftback.domain.dto;

import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

   @JsonIgnore
   private String password;

   @Builder.Default
   private Role role = Role.USER;
   
   @Builder.Default
   private AuthProvider authProvider = AuthProvider.LOCAL;

   private GroupDto group;
   
   @JsonManagedReference
   private Set<NotificationDto> notifications;

   private WalletDto wallet;

   private String bankName;
   private String bankAccountNumber;
   
   @CreationTimestamp
   private LocalDateTime createdAt;
   
   @UpdateTimestamp
   private LocalDateTime updatedAt;
}
