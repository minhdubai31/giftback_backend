package com.minhdubai.Giftback.service;

import com.minhdubai.Giftback.domain.dto.CashbackDto;
import com.minhdubai.Giftback.domain.dto.TransactionDto;
import com.minhdubai.Giftback.domain.entity.AffiliateNetwork;
import com.minhdubai.Giftback.domain.entity.Cashback;
import com.minhdubai.Giftback.domain.entity.Transaction;
import com.minhdubai.Giftback.mapper.Mapper;
import com.minhdubai.Giftback.repository.AffiliateNetworkRepository;
import com.minhdubai.Giftback.repository.CashbackRepository;
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

import com.minhdubai.Giftback.domain.dto.common.ResponseDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CashbackService {
   private final CashbackRepository cashbackRepository;
   private final AffiliateNetworkRepository affiliateNetworkRepository;
   private final TransactionRepository transactionRepository;
   private final Mapper<Cashback, CashbackDto> cashbackMapper;
   private final Mapper<Transaction, TransactionDto> transactionMapper;

   public ResponseDto createCashback(CashbackDto cashbackDto) {
      Cashback cashback = Cashback.builder()
            .amount(cashbackDto.getAmount())
            .earnedAt(cashbackDto.getEarnedAt())
            .transaction(transactionMapper.mapFrom(cashbackDto.getTransaction()))
            .build();
      Cashback savedCashback = cashbackRepository.save(cashback);
      CashbackDto savedCashbackDto = cashbackMapper.mapTo(savedCashback);
      return ResponseDto.builder()
            .status(201)
            .message("Cashback created successfully")
            .data(savedCashbackDto)
            .build();
   }

   public ResponseDto getAllCashbacks() {
      List<CashbackDto> cashbackDtos = cashbackRepository.findAll()
            .stream()
            .map(cashback -> cashbackMapper.mapTo(cashback))
            .toList();
      return ResponseDto.builder()
            .status(200)
            .message("Cashbacks retrieved successfully")
            .data(cashbackDtos)
            .build();
   }

   public ResponseDto getCashbackById(Integer id) {
      Cashback cashback = cashbackRepository.findById(id).orElse(null);
      if (cashback != null) {
         CashbackDto cashbackDto = cashbackMapper.mapTo(cashback);
         return ResponseDto.builder()
               .status(200)
               .message("Cashback found")
               .data(cashbackDto)
               .build();
      }
      return ResponseDto.builder()
            .status(404)
            .message("Cashback not found")
            .build();
   }

   public ResponseDto updateCashback(CashbackDto cashbackDto) {
      Cashback cashback = Cashback.builder()
            .id(cashbackDto.getId())
            .amount(cashbackDto.getAmount())
            .earnedAt(cashbackDto.getEarnedAt())
            .build();
      Cashback updatedCashback = cashbackRepository.save(cashback);
      CashbackDto updatedCashbackDto = cashbackMapper.mapTo(updatedCashback);
      return ResponseDto.builder()
            .status(200)
            .message("Cashback updated successfully")
            .data(updatedCashbackDto)
            .build();
   }

   public void deleteCashback(Integer id) {
      cashbackRepository.deleteById(id);
   }

   public ResponseDto updateCashbackFromNetwork() {
      List<AffiliateNetwork> networks = affiliateNetworkRepository.findAll();
      if (networks.size() == 0) {
         return ResponseDto.builder()
               .status(500)
               .message("No affiliate network has been set")
               .build();
      }

      AffiliateNetwork network = networks.getFirst();
      RestTemplate restTemplate = new RestTemplate();

      String url = network.getApiMap().getGetTransactionApi() + "?is_confirmed=1";
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
               String transactionId = (String) transactionData.get("transaction_id");
               Transaction transaction = transactionRepository
                     .findByTransactionId(transactionId).orElse(null);
               
                     if (transaction == null) {
                  continue;
               }
               // Map the response data to cashbackDto
               CashbackDto cashbackDto = CashbackDto.builder()
                     .transaction(transactionMapper.mapTo(transaction))
                     .amount(BigDecimal.valueOf((Double) transactionData.get("commission")))
                     .earnedAt(LocalDateTime.parse((String) transactionData.get("confirmed_time")))
                     .build();

               Cashback cashback = cashbackMapper.mapFrom(cashbackDto);
               cashbackRepository.save(cashback);
            }
            return ResponseDto.builder()
                  .status(200)
                  .message("Cashbacks updated successfully")
                  .build();
         } else {
            return ResponseDto.builder()
                  .status(500)
                  .message("Failed to fetch cashbacks from network")
                  .build();
         }
      } catch (Exception e) {
         return ResponseDto.builder()
               .status(500)
               .message("An error occurred while fetching cashbacks: " + e.getMessage())
               .build();
      }
   }

   public ResponseDto getCashbacksByUser(Integer userId) {
      List<Cashback> cashbacks = cashbackRepository.findAllByTransactionUserId(userId);
      List<CashbackDto> dtos = cashbacks.stream().map(cashbackMapper::mapTo).toList();
      return ResponseDto.builder()
         .status(200)
         .message("Cashback retrieved successfully")
         .data(dtos)
         .build();
   }
}
