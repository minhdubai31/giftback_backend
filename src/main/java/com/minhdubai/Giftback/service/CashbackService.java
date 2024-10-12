package com.minhdubai.Giftback.service;

import com.minhdubai.Giftback.domain.entity.Cashback;
import com.minhdubai.Giftback.repository.CashbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CashbackService {
   @Autowired
   private CashbackRepository cashbackRepository;

   public Cashback createCashback(Cashback cashback) {
      return cashbackRepository.save(cashback);
   }

   public List<Cashback> getAllCashbacks() {
      return cashbackRepository.findAll();
   }

   public Cashback getCashbackById(Integer id) {
      return cashbackRepository.findById(id).orElse(null);
   }

   public Cashback updateCashback(Cashback cashback) {
      return cashbackRepository.save(cashback);
   }

   public void deleteCashback(Integer id) {
      cashbackRepository.deleteById(id);
   }
}
