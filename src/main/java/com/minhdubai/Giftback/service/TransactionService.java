package com.minhdubai.Giftback.service;

import com.minhdubai.Giftback.domain.entity.AffiliateNetwork;
import com.minhdubai.Giftback.domain.entity.Transaction;
import com.minhdubai.Giftback.mapper.Mapper;
import com.minhdubai.Giftback.repository.AffiliateNetworkRepository;
import com.minhdubai.Giftback.repository.TransactionRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.minhdubai.Giftback.domain.dto.TransactionDto;
import com.minhdubai.Giftback.domain.dto.common.ResponseDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TransactionService {
   private final TransactionRepository transactionRepository;
   private final Mapper<Transaction, TransactionDto> transactionMapper;
   private final AffiliateNetworkRepository affiliateNetworkRepository;

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

   public ResponseDto updateTransactionFromNetwork() {
      List<AffiliateNetwork> networks = affiliateNetworkRepository.findAll();
      if (networks.size() == 0) {
         return ResponseDto.builder()
               .status(500)
               .message("No affiliate network has been set")
               .build();
      }

      AffiliateNetwork network = networks.getFirst();
      RestTemplate restTemplate = new RestTemplate();

      String url = network.getApiMap().getGetTransactionApi();
      String token = network.getAuthorizeToken();

      HttpHeaders headers = new HttpHeaders();
      headers.add("Authorization", "Token " + token);
      headers.add("Content-Type", "application/json");

      HttpEntity<String> entity = new HttpEntity<>(headers);
      try {
         ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
               url,
               HttpMethod.GET,
               entity,
               new ParameterizedTypeReference<Map<String, Object>>() {
               });

         if (response.getStatusCode() == HttpStatus.OK) {
            List<Map<String, Object>> data = (List<Map<String, Object>>) response.getBody().get("data");
            for (Map<String, Object> transactionData : data) {
               // Map the response data to TransactionDto
               TransactionDto transactionDto = new TransactionDto();
               transactionDto.setProductName((String) transactionData.get("product_name"));
               transactionDto.setProductImage((String) transactionData.get("product_image"));
               transactionDto.setTotalAmount(BigDecimal.valueOf((Double) transactionData.get("transaction_value")));
               transactionDto.setCashbackAmount(BigDecimal.valueOf((Double) transactionData.get("commission")));
               transactionDto.setStatus((Integer) transactionData.get("status"));
               transactionDto.setReasonReject((String) transactionData.get("reason_rejected"));
               transactionDto.setTransactionDate(LocalDateTime.parse((String) transactionData.get("transaction_time")));

               // Save the transaction to the database
               Transaction transaction = transactionMapper.mapFrom(transactionDto);
               transactionRepository.save(transaction);
            }
            return ResponseDto.builder()
                  .status(200)
                  .message("Transactions updated successfully")
                  .build();
         } else {
            return ResponseDto.builder()
                  .status(500)
                  .message("Failed to fetch transactions from network")
                  .build();
         }
      } catch (Exception e) {
         return ResponseDto.builder()
               .status(500)
               .message("An error occurred while fetching transactions: " + e.getMessage())
               .build();
      }

   }
}
