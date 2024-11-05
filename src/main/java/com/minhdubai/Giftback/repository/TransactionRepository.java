package com.minhdubai.Giftback.repository;

import com.minhdubai.Giftback.domain.entity.Transaction;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    Optional<Transaction> findByTransactionId(String transactionId);
}
