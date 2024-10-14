package com.minhdubai.Giftback.service;

import com.minhdubai.Giftback.domain.entity.Payout;
import com.minhdubai.Giftback.domain.entity.User;
import com.minhdubai.Giftback.mapper.Mapper;
import com.minhdubai.Giftback.domain.dto.PayoutDto;
import com.minhdubai.Giftback.repository.PayoutRepository;
import com.minhdubai.Giftback.repository.UserRepository;
import com.minhdubai.Giftback.domain.dto.common.ResponseDto;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayoutService {
   private PayoutRepository payoutRepository;
   private UserRepository userRepository;
   private Mapper<Payout, PayoutDto> payoutMapper;

   public ResponseDto createPayout(PayoutDto payoutDto) {
      User user = userRepository.findById(payoutDto.getUser().getId())
            .orElseThrow(() -> new RuntimeException("User not found with ID: " + payoutDto.getUser().getId()));
      Payout payout = Payout.builder()
            .user(user)
            .amount(payoutDto.getAmount())
            .method(payoutDto.getMethod())
            .status(payoutDto.getStatus())
            .build();
      Payout savedPayout = payoutRepository.save(payout);
      PayoutDto savedPayoutDto = payoutMapper.mapTo(savedPayout);
      return ResponseDto.builder()
            .status(201)
            .message("Payout created successfully")
            .data(savedPayoutDto)
            .build();
   }

   public ResponseDto getAllPayouts() {
      List<PayoutDto> payoutDtos = payoutRepository.findAll().stream()
         .map(payoutMapper::mapTo)
         .toList();
      return ResponseDto.builder()
            .status(200)
            .message("Payouts retrieved successfully")
            .data(payoutDtos)
            .build();
   }

   public ResponseDto getPayoutById(Integer id) {
      Payout payout = payoutRepository.findById(id).orElse(null);
      if (payout != null) {
         PayoutDto payoutDto = payoutMapper.mapTo(payout);
         return ResponseDto.builder()
                .status(200)
                .message("Payout found")
                .data(payoutDto)
                .build();
      }
      return ResponseDto.builder()
            .status(404)
            .message("Payout not found")
            .build();
   }
   public ResponseDto updatePayout(Integer id, PayoutDto payoutDto) {
      Payout payout = payoutRepository.findById(id).orElse(null);
      if (payout != null) {
         User user = userRepository.findById(payoutDto.getUser().getId())
               .orElseThrow(() -> new RuntimeException("User not found with ID: " + payoutDto.getUser().getId()));
         payout.setUser(user);
         payout.setAmount(payoutDto.getAmount());
         payout.setMethod(payoutDto.getMethod());
         payout.setStatus(payoutDto.getStatus());
         Payout updatedPayout = payoutRepository.save(payout);
         PayoutDto updatedPayoutDto = payoutMapper.mapTo(updatedPayout);
         return ResponseDto.builder()
               .status(200)
               .message("Payout updated successfully")
               .data(updatedPayoutDto)
               .build();
      }
      return ResponseDto.builder()
            .status(404)
            .message("Payout not found")
            .build();
   }

   public void deletePayout(Integer id) {
      payoutRepository.deleteById(id);
   }
}
