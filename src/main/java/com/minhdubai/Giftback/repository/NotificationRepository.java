package com.minhdubai.Giftback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minhdubai.Giftback.domain.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
   
}
