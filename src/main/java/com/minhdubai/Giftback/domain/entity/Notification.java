package com.minhdubai.Giftback.domain.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "notifications")
public class Notification {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

   private String message;

   @Builder.Default
   private Boolean is_read = false;

   @ManyToOne
   @JoinColumn(name = "user_id")
   private User user;
   
   @CreationTimestamp
   private LocalDateTime createdAt;

   @UpdateTimestamp
   private LocalDateTime updatedAt; 
}
