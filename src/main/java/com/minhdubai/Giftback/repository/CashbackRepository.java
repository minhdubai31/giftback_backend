package com.minhdubai.Giftback.repository;

import com.minhdubai.Giftback.domain.entity.Cashback;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CashbackRepository extends JpaRepository<Cashback, Integer> {
    // Additional query methods can be defined here
    List<Cashback> findAllByTransactionUserId(Integer userId);
}
