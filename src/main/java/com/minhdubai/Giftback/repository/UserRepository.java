package com.minhdubai.Giftback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minhdubai.Giftback.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
   User findByUsername(String username);
   boolean existsByUsername(String username);
}
