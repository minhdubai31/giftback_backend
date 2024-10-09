package com.minhdubai.Giftback.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.minhdubai.Giftback.domain.entity.Notification;
import com.minhdubai.Giftback.domain.entity.User;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
   List<Notification> findByUser(User user);
}
