package com.minhdubai.Giftback.service;

import com.minhdubai.Giftback.domain.entity.Transaction;
import com.minhdubai.Giftback.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
   @Autowired
   private TransactionRepository transactionRepository;

   public Transaction createTransaction(Transaction transaction) {
      return transactionRepository.save(transaction);
   }

   public List<Transaction> getAllTransactions() {
      return transactionRepository.findAll();
   }

   public Transaction getTransactionById(Integer id) {
      return transactionRepository.findById(id).orElse(null);
   }

   public Transaction updateTransaction(Transaction transaction) {
      return transactionRepository.save(transaction);
   }

   public void deleteTransaction(Integer id) {
      transactionRepository.deleteById(id);
   }
}
