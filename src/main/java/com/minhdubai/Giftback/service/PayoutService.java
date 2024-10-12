package com.minhdubai.Giftback.service;

import com.minhdubai.Giftback.domain.entity.Payout;
import com.minhdubai.Giftback.domain.entity.User;
import com.minhdubai.Giftback.dto.PayoutDTO;
import com.minhdubai.Giftback.repository.PayoutRepository;
import com.minhdubai.Giftback.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayoutService {
   private PayoutRepository payoutRepository;
   private UserRepository userRepository;

   public Payout createPayout(PayoutDTO payoutDTO) {
      User user = userRepository.findById(payoutDTO.getUserId())
            .orElseThrow(() -> new RuntimeException("User not found with ID: " + payoutDTO.getUserId()));
      Payout payout = Payout.builder()
            .user(user)
            .amount(payoutDTO.getAmount())
            .method(payoutDTO.getMethod())
            .status(payoutDTO.getStatus())
            .build();
      return payoutRepository.save(payout);
   }

   public List<Payout> getAllPayouts() {
      return payoutRepository.findAll();
   }

   public Payout getPayoutById(Integer id) {
      return payoutRepository.findById(id).orElse(null);
   }

   public Payout updatePayout(Integer id, PayoutDTO payoutDTO) {
      Payout payout = payoutRepository.findById(id).orElse(null);
      if (payout != null) {
         User user = userRepository.findById(payoutDTO.getUserId())
               .orElseThrow(() -> new RuntimeException("User not found with ID: " + payoutDTO.getUserId()));
         payout.setUser(user);
         payout.setAmount(payoutDTO.getAmount());
         payout.setMethod(payoutDTO.getMethod());
         payout.setStatus(payoutDTO.getStatus());
         return payoutRepository.save(payout);
      }
      return null;
   }

   public void deletePayout(Integer id) {
      payoutRepository.deleteById(id);
   }
}
