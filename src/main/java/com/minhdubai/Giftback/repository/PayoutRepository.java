package com.minhdubai.Giftback.repository;

import com.minhdubai.Giftback.domain.entity.Payout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayoutRepository extends JpaRepository<Payout, Integer> {
    // Additional query methods can be defined here
}
