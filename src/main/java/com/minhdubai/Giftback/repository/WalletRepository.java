package com.minhdubai.Giftback.repository;

import com.minhdubai.Giftback.domain.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Integer> {
    // Additional query methods can be defined here
}
