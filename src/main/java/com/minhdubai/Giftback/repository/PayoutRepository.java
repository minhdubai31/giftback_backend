package com.minhdubai.Giftback.repository;

import com.minhdubai.Giftback.domain.entity.Payout;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PayoutRepository extends JpaRepository<Payout, Integer> {
    List<Payout> findAllByUserId(Integer userId);
}
