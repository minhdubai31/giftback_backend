package com.minhdubai.Giftback.repository;

import com.minhdubai.Giftback.domain.entity.Cashback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashbackRepository extends JpaRepository<Cashback, Integer> {
    // Additional query methods can be defined here
}
