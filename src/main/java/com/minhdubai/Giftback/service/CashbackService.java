package com.minhdubai.Giftback.service;

import com.minhdubai.Giftback.domain.dto.CashbackDto;
import com.minhdubai.Giftback.domain.entity.Cashback;
import com.minhdubai.Giftback.mapper.Mapper;
import com.minhdubai.Giftback.repository.CashbackRepository;
import org.springframework.stereotype.Service;
import com.minhdubai.Giftback.domain.dto.common.ResponseDto;

import java.util.List;

@Service
public class CashbackService {
   private CashbackRepository cashbackRepository;
   private Mapper<Cashback, CashbackDto> cashbackMapper;

   public ResponseDto createCashback(CashbackDto cashbackDto) {
      Cashback cashback = Cashback.builder()
              .amount(cashbackDto.getAmount())
              .earnedAt(cashbackDto.getEarnedAt())
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
}
