package com.minhdubai.Giftback.service;

import com.minhdubai.Giftback.domain.entity.Transaction;
import com.minhdubai.Giftback.mapper.Mapper;
import com.minhdubai.Giftback.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import com.minhdubai.Giftback.domain.dto.TransactionDto;
import com.minhdubai.Giftback.domain.dto.common.ResponseDto;

import java.util.List;

@Service
public class TransactionService {
   private TransactionRepository transactionRepository;
   private Mapper<Transaction, TransactionDto> transactionMapper;

   public ResponseDto createTransaction(TransactionDto transactionDto) {
      Transaction transaction = transactionMapper.mapFrom(transactionDto);
      Transaction savedTransaction = transactionRepository.save(transaction);
      TransactionDto savedTransactionDto = transactionMapper.mapTo(savedTransaction);
      return ResponseDto.builder()
              .status(201)
              .message("Transaction created successfully")
              .data(savedTransactionDto)
              .build();
   }

   public ResponseDto getAllTransactions() {
      List<Transaction> transactions = transactionRepository.findAll();
      List<TransactionDto> transactionDtos = transactions.stream()
              .map(transactionMapper::mapTo)
              .toList();
      return ResponseDto.builder()
              .status(200)
              .message("Transactions retrieved successfully")
              .data(transactionDtos)
              .build();
   }

   public ResponseDto getTransactionById(Integer id) {
      Transaction transaction = transactionRepository.findById(id).orElse(null);
      if (transaction != null) {
         TransactionDto transactionDto = transactionMapper.mapTo(transaction);
         return ResponseDto.builder()
                 .status(200)
                 .message("Transaction found")
                 .data(transactionDto)
                 .build();
      }
      return ResponseDto.builder()
              .status(404)
              .message("Transaction not found")
              .build();
   }

   public ResponseDto updateTransaction(TransactionDto transactionDto) {
      Transaction transaction = transactionMapper.mapFrom(transactionDto);
      Transaction updatedTransaction = transactionRepository.save(transaction);
      TransactionDto updatedTransactionDto = transactionMapper.mapTo(updatedTransaction);
      return ResponseDto.builder()
              .status(200)
              .message("Transaction updated successfully")
              .data(updatedTransactionDto)
              .build();
   }

   public void deleteTransaction(Integer id) {
      transactionRepository.deleteById(id);
   }
}
