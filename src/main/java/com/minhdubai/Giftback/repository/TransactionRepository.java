package com.minhdubai.Giftback.repository;

import com.minhdubai.Giftback.domain.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    // Additional query methods can be defined here
}
